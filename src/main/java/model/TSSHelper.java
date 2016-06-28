package model;


import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class TSSHelper {

    public static String printSystem(List<Equation> system) {
        StringBuilder sb = new StringBuilder();

        for (Equation e: system) {
            sb.append(e.toString());
            sb.append("\n");
        }

        return sb.toString();
    }


    public static void resizeSystem(List<Equation> system, int p) {
        int k = 0;
        for (Equation e: system) {
            for (int i = 0; i < p; i++) {
                if (i == k) e.getCoefficients().add(-1.0);
                else e.getCoefficients().add(0.0);
            }
            k++;
        }
    }


    public static void resizeSystem2(List<Equation> system, int p) {
        for (Equation e: system) {
            e.getCoefficients().subList(e.getCoefficients().size() - p, e.getCoefficients().size()).clear();
        }
    }
}
