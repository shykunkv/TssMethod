package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EquationHelper {

    public static Equation mult(Equation e, Double d) {
        List<Double> coeffs = new ArrayList<>();
        for (int i = 0; i < e.getCoefficients().size(); i++) {
            coeffs.add(e.getCoefficients().get(i) * d);
        }
        return new Equation(coeffs);
    }

    public static Equation add(List<Equation> eqs) {
        Equation result = new Equation();
        for (int i = 0; i < eqs.get(0).getCoefficients().size(); i++) {
            Double temp = 0.0;
            for (int j = 0; j < eqs.size(); j++) {
                temp += eqs.get(j).getCoefficients().get(i);
            }
            result.getCoefficients().add(temp);
        }

        return result;
    }

    public static Equation mult(Equation e, List<Equation> system) {
        List<Equation> res = new ArrayList<>(system);
        for (int i = 0; i < e.getCoefficients().size(); i++) {
            res.set(i, mult(res.get(i), e.getCoefficients().get(i)));
        }
        return add(res);
    }

    public static List<Equation> getNewSystem(List<Equation> basis, List<Equation> basis2) {
        List<Equation> result = new ArrayList<>();
        for (int i = 0; i < basis2.size(); i++) {
            result.add(mult(basis2.get(i), basis));
        }
        return result;
    }
}
