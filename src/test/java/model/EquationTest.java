package model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EquationTest {

    @Test
    public void EquationToStringTest() {
        Equation input = new Equation("1 2 3 4");
        String actual = input.toString();

        String expected = "[1.0, 2.0, 3.0, 4.0]";

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void EquationToEmptyStringTest() {
        Equation input = new Equation();
        String actual = input.toString();

        String expected = "[]";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void EquationToDoubleStringTest() {
        Equation input = new Equation("2.5 5.7 -2.3 0");
        String actual = input.toString();

        String expected = "[2.5, 5.7, -2.3, 0.0]";

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getResultTest() {
        Equation equation = new Equation("0.5 0.2 1.0 0.0 1.3");

        Double actual = equation.result(new Equation("1.1 -2.1 0 0 0"));
        Assert.assertEquals(actual, new Double(0.13));

        actual = equation.result(new Equation("0 0 1 0 0"));
        Assert.assertEquals(actual, new Double(1.0));
    }

    @Test
    public void getResultFromSystemTest() {
        Equation equation = new Equation("0.5 0.2 1.0 0.0 -1.3");
        List<Equation> system = new ArrayList<>();
        system.add(new Equation("1.1 -2.1 0 0 0"));
        system.add(new Equation("0 0 1 0 0"));
        system.add(new Equation("1 0 0 -2.1 0"));
        system.add(new Equation("2.3 0 0 0 -2.1"));

        Equation actual = equation.resultFromSystem(system);

        Equation expected = new Equation("0.13 1 0.5 3.88");

        Assert.assertEquals(expected.toString(), actual.toString());
    }
}
