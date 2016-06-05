package model;


import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class TSSHelper {

    public static String printSystem(List<Equation> system) {
        StringBuilder sb = new StringBuilder();

        for (Equation e: system) {
            sb.append(e.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

}
