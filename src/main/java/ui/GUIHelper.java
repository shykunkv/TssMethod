package ui;

import model.Equation;
import model.MatrixDto;
import model.TSSHelper;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GUIHelper {

    private static String[] signs = {">=", "<=", "=="};

    public static boolean validateSize(JTextField M, JTextField N) {
        boolean res = true;
        try {
            Integer d = Integer.parseInt(M.getText());
            if (d <= 0) {
                N.setForeground(new Color(255, 105, 87));
                res = false;
            }
        } catch (NumberFormatException ex) {
            N.setForeground(new Color(255, 105, 87));
            ex.printStackTrace();
            res = false;
        }

        try {
            Integer d = Integer.parseInt(N.getText());
            if (d <= 0) {
                N.setForeground(new Color(255, 105, 87));
                res = false;
            }
        } catch (NumberFormatException ex) {
            N.setForeground(new Color(255, 105, 87));
            ex.printStackTrace();
            res = false;
        }

        return res;
    }

    public static boolean validateMatrix(List<JTextField> matrix) {
        boolean res = true;
        for (JTextField textField: matrix) {
            try {
                Double d = Double.parseDouble(textField.getText());
            } catch (NumberFormatException ex) {
                textField.setForeground(new Color(255, 105, 87));
                ex.printStackTrace();
                res = false;
            }
        }

        return res;
    }

    public static void addTextFields(List<JTextField> matrix, List<JTextField> b,  JPanel lPanel, Integer m, Integer n) {

        for (int i = 0; i < m; i++) {
            for (int j  = 0; j < n; j++) {
                JLabel label = new JLabel();
                if (j == 0) label.setText("  x" + j + "*");
                else label.setText(" +  x"+ j +"*");
                lPanel.add(label);

                JTextField matrixTextField = new JTextField(3);
                matrixTextField.setText("0");

                matrix.add(matrixTextField);
                lPanel.add(matrixTextField);
            }

            JComboBox signList = new JComboBox(signs);
            signList.setSelectedIndex(0);
            lPanel.add(signList);

            JTextField bField = new JTextField();
            bField.setText("0");

            b.add(bField);
            lPanel.add(bField);
        }
    }

    public static void addTextFields(List<JTextField> matrixFromFile, List<JTextField> matrix, List<JTextField> b,  JPanel lPanel, Integer m, Integer n) {

        for (int i = 0; i < m; i++) {
            for (int j  = 0; j < n; j++) {
                JLabel label = new JLabel();
                if (j == 0) label.setText("  x" + j + "*");
                else label.setText(" +  x"+ j +"*");
                lPanel.add(label);

                JTextField matrixTextField = new JTextField(3);
                matrixTextField.setText(matrixFromFile.get(i * n + j).getText());

                matrix.add(matrixTextField);
                lPanel.add(matrixTextField);
            }

            JComboBox signList = new JComboBox(signs);
            signList.setSelectedIndex(0);
            lPanel.add(signList);

            JTextField bField = new JTextField();
            bField.setText("0");

            b.add(bField);
            lPanel.add(bField);
        }
    }

    public static List<Equation> getSystemFromGUI(List<JTextField> matrix, int m, int n) {
        List<Equation> system = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            Equation temp = new Equation();
            for (int j = 0; j < n; j++) {
                Double d = Double.parseDouble(matrix.get(i * n + j).getText());
                temp.getCoefficients().add(d);
            }
            system.add(temp);
        }

        return system;
    }

    public static MatrixDto getMatrixFromFile(File file) throws IOException {
        List<JTextField> result = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String currLine;
        int n = 0;
        int m = 0;

        while ((currLine = reader.readLine()) != null) {
            currLine = currLine.replace("]", "");
            currLine = currLine.replace("[", "");
            String[] strings = currLine.split(" ");
            n = strings.length;
            for (String s: strings) {
                JTextField textField = new JTextField();
                textField.setText(s);
                result.add(textField);
            }
            m++;
        }
        reader.close();
        return new MatrixDto(result, n, m);
    }

    public static void printSystemToFile(File file, List<Equation> system) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (Equation e: system) {
            writer.write(e.toString());
            writer.newLine();
        }
        writer.close();
    }

}
