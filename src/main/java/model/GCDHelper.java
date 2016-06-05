package model;


import java.util.List;

public class GCDHelper {

    public static double GCD(double a, double b) {
        if (b == 0) return a;
        else return GCD(b, a%b);
    }

    public static Double GCD(List<Double> doubleList) {
        Double res = doubleList.get(0);
        for (int i = 1; i < doubleList.size(); i++) {
            res = GCD(res, doubleList.get(i));
        }

        return res;
    }

    public static Equation GCD(Equation equation) {
        Double gcd = GCD(equation.getCoefficients());
        for (int i = 0; i < equation.getCoefficients().size(); i++) {
            equation.getCoefficients().set(i, equation.getCoefficients().get(i) / gcd);
        }
        return equation;
    }
}
