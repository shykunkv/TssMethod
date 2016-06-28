package TSS;


import model.Equation;
import model.TSSHelper;
import org.junit.Assert;
import org.junit.Test;
import tss.TSS;

import static model.EquationHelper.mult;
import static model.EquationHelper.toCleanString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TSSTest {

    @Test
    public void shouldReturnEmptyBasisTest() {
        Equation equation = new Equation();
        List<Equation> actual = TSS.getBasisForEquation(equation);

        Assert.assertEquals(actual, Collections.emptyList());
    }

    @Test
    public void shouldReturnBasisTest() {
        List<Equation> actual = TSS.getBasisForEquation(new Equation("1.2 1.5 0.7 0.4"));

        List<Equation> expected = new ArrayList<>();
        expected.add(new Equation("1.5 -1.2 0 0"));
        expected.add(new Equation("0.7 0 -1.2 0"));
        expected.add(new Equation("0.4 0 0 -1.2"));

        Assert.assertEquals(actual.toString(), expected.toString());
    }


    @Test
    /**
     * [0.7371, -1.46055, -0.07644, 0.05869, 0.0]
     * [1.2899, 1,290 -2,585 -0,052 0,000 0,059]
     */
    public void shouldReturnBasisFromSystemTest() {
        List<Equation> system = new ArrayList<>();
        system.add(new Equation("2.1 1.1 0 1 2.3"));
        system.add(new Equation("0.5 0.2 1 0 -1.3"));
        system.add(new Equation("0.1 0 2.5 2 0"));

        List<Equation> actual = TSS.getBasisForSystem(system);


        List<Equation> expected = new ArrayList<>();
        expected.add(new Equation("0.737 -1.461 -0.076 0.059 0"));
        expected.add(new Equation("1.290 -2.585 -0.052 0 0.059"));

        System.out.println(TSSHelper.printSystem(actual));

        Assert.assertEquals(actual.toString(), expected.toString());
    }

    @Test
    public void shouldReturnBasisFromSystemTest2() {
        List<Equation> system = new ArrayList<>();
        system.add(new Equation("-3 -4 5 -6 -2"));
        system.add(new Equation("2 3 -2 1 1"));
        system.add(new Equation("1 -1 -1 2 -1"));

        System.out.println(TSS.getBasisForSystem(system));
    }



    @Test
    public void simpleTest() {
        Equation e1 = new Equation("2 2 2 2 2");
        Equation e2 = mult(e1, 5.0);

        System.out.println(e1);
        System.out.println(e2);
    }

    @Test
    public void simpleTest2() {
        List<Equation> system = new ArrayList<>();
        system.add(new Equation("-3 -4 5 -6"));
        system.add(new Equation("2 3 -2 1"));
        system.add(new Equation("1 -1 -1 2"));

        System.out.println(TSSHelper.printSystem(TSS.getBasisForSystem2(system)));
    }
}
