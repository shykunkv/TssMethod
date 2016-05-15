package tss;

import model.Equation;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static model.EquationHelper.getNewSystem;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class TSS {


    /**
     * Get basis from single equation
     * Basis size is num('x') - 1
     */
    public static List<Equation> getBasisForEquation(Equation equation) {
        if (equation.getCoefficients().size() == 0) {
            return Collections.emptyList();
        }
        double firstCoeff = equation.getCoefficients().get(0);

        int size = equation.getCoefficients().size();
        List<Equation> result = new ArrayList<>(size);

        for (int i = 1; i < size; i++) {
            Equation eq = new Equation(size);
            if (firstCoeff != 0) eq.getCoefficients().set(i, -firstCoeff);
            eq.getCoefficients().set(0, equation.getCoefficients().get(i));
            if (eq.getCoefficients().get(0).equals(0.0)) {
                eq.getCoefficients().set(i, 1.0);
            }
            result.add(eq);
        }

        assertThat(equation.getCoefficients().size() - 1, is(result.size()));

        return result;
    }

    public static List<Equation> getBasisForSystem(List<Equation> system) {
        assertThat(system.size(), not(0));
        List<Equation> tempBasis = getBasisForEquation(system.get(0));

        for (int i = 0; i < system.size() - 1; i++) {

            Equation tempEquation = system.get(i+1).resultFromSystem(tempBasis);

            assertThat(tempBasis.size(), is(tempEquation.getCoefficients().size()));

            List<Equation> tempBasis2 = getBasisForEquation(tempEquation);

            assertThat(tempBasis.size(), is(tempBasis2.size() + 1));
            assertThat(tempBasis.size(), is(tempBasis2.get(0).getCoefficients().size()));

            tempBasis = getNewSystem(tempBasis, tempBasis2);

            assertThat(tempBasis2.size(), is(tempBasis.size()));
        }
        return tempBasis;
    }

}
