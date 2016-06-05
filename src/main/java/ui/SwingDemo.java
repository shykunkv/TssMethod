package ui;

import model.Equation;
import tss.TSS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class SwingDemo {
    private JFrame mainFrame;
    private JPanel matrixPanel;
    private JPanel outputPanel;
    private JPanel lPanel;
    private JPanel sizePanel;
    private JPanel resultPanel;
    private List<JTextField> matrix = new ArrayList<>();
    private List<JTextField> b = new ArrayList<>();
    private JTextField N;
    private JTextField M;
    private JTextPane resutlText;

    private JFileChooser fileChooser = new JFileChooser();

    public SwingDemo(int m, int n) {
        prepareGUI(m, n);
    }

    public SwingDemo(){
        generateDefaultGUI();
    }

    public static void main(String[] args){
        SwingDemo swingControlDemo = new SwingDemo();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI(int m, int n){
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new GridLayout(2, 1));

        matrixPanel = new JPanel();
        matrixPanel.setLayout(new GridLayout(1, 1));

        outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(2, 1));

        generateSizePanel(m, n);

        lPanel = new JPanel();
        lPanel.setLayout(new GridLayout(m, n+2));

        GUIHelper.addTextFields(matrix, b, lPanel, m, n);

        matrixPanel.add(lPanel);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
                System.exit(0);
            }
        });

        mainFrame.add(matrixPanel);
        mainFrame.add(outputPanel);

        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
    }

    private void generateSizePanel(int m, int n) {
        sizePanel = new JPanel();
        sizePanel.setLayout(new GridLayout(2, 3));
        N = new JTextField();
        N.setText(n + "");
        M = new JTextField();
        M.setText(m + "");

        sizePanel.add(N);
        sizePanel.add(M);

        JButton button = new JButton("Generate");
        button.setActionCommand("Generate");
        button.addActionListener(new ButtonClickListener());
        sizePanel.add(button);

        button = new JButton("Find TSS basis");
        button.setActionCommand("TSS");
        button.addActionListener(new ButtonClickListener());
        sizePanel.add(button);

        button = new JButton("Save system");
        button.setActionCommand("SAVE");
        button.addActionListener(new ButtonClickListener());
        sizePanel.add(button);

        button = new JButton("Load system");
        button.setActionCommand("LOAD");
        button.addActionListener(new ButtonClickListener());
        sizePanel.add(button);

        resultPanel = new JPanel();
        resutlText = new JTextPane();
        resultPanel.add(resutlText);

        outputPanel.add(sizePanel);
        outputPanel.add(resultPanel);
    }

    private void outputText(String text) {
        resutlText.setText(text);
    }

    private void generateDefaultGUI() {
        prepareGUI(5, 5);
    }

    private void showEventDemo() {
        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();
            if (command.equals("Submit"))  {

            } else if (command.equals("Generate")) {
                if (GUIHelper.validateSize(N, M)) {
                    int n = Integer.parseInt(N.getText());
                    int m = Integer.parseInt(M.getText());

                    mainFrame.setVisible(false);
                    mainFrame.dispose();
                    SwingDemo swingControlDemo = new SwingDemo(m, n);
                    swingControlDemo.showEventDemo();
                } else {
                    outputText("WRONG SIZE");
                }
            } else if (command.equals("TSS")) {
                int n = Integer.parseInt(N.getText());
                int m = Integer.parseInt(M.getText());

                assertThat(matrix.size() % n, is(0));

                if (GUIHelper.validateMatrix(matrix)) {
                    List<Equation> system = GUIHelper.getSystemFromGUI(matrix, m, n);
                    outputText(TSS.getBasisForSystem(system).toString());
                } else {
                    outputText("WRONG INPUT");
                }

            } else if (command.equals("LOAD")) {
                int result = fileChooser.showOpenDialog(mainFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try {
                        List<JTextField> matrixFromFile = GUIHelper.getMatrixFromFile(selectedFile);
                    } catch (IOException ex) {
                        outputText("WRONG FILE");
                        ex.printStackTrace();
                    }
                }
            } else if (command.equals("SAVE")) {
                int result = fileChooser.showSaveDialog(mainFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    //TODO: validate system
                    //TODO: validate size
                    try {
                        GUIHelper.printSystemToFile(file, GUIHelper.getSystemFromGUI(matrix, Integer.parseInt(M.getText()), Integer.parseInt(N.getText())));
                    } catch (IOException ex) {
                        outputText("WRONG FILE");
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
}
