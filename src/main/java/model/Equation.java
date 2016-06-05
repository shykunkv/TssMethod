package model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Equation {
    private List<Double> coefficients;

    public Equation() {
        coefficients = new ArrayList<>();
    }

    public Equation(Equation e) {
        this.coefficients = e.getCoefficients();
    }

    public Equation(List<Double> coeffs) {
        this.coefficients = coeffs;
    }

    public Equation(int size) {
        coefficients = new ArrayList<>();
        for (int i = 0; i < size; i++) coefficients.add(0.0);
    }

    public Equation(String coeffs) {
        String[] coeffArray = coeffs.split(" ");
        this.coefficients = Arrays.stream(coeffArray)
                .map(Double::parseDouble)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("[");
        for (Double d : coefficients) {
            if (d.equals(-0.0) || d.equals(0.0)) sb.append(String.format("0.000"));
            else sb.append(String.format("%.3f", d));
            sb.append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    public List<Double> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Double> coefficients) {
        this.coefficients = coefficients;
    }

    public Double result(Equation e) {
        double res = 0.0;
        for (int i = 0; i < coefficients.size(); i++) {
            res += e.getCoefficients().get(i) * this.coefficients.get(i);
        }
        return res;
    }

    public Equation resultFromSystem(List<Equation> system) {
        List<Double> coeffs = new ArrayList<>();

        for (int i = 0; i < system.size(); i++) {
            coeffs.add(result(system.get(i)));
        }

        Equation res = new Equation();
        res.setCoefficients(coeffs);

        return res;
    }



}
