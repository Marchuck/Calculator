package pl.marczak.calculator.calc.operations;

/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 16.11.2016.
 */

public class Divide implements Operation {

    @Override
    public String operationSymbol() {
        return "/";
    }

    @Override
    public double operate(double number1, double number2) throws NumberFormatException {
        if (Double.compare(number2, 0) == 0) throw new NumberFormatException("Divide by zero!");
        return number1 / number2;
    }

}
