package pl.marczak.calculator.calc.operations;

/**
 * Project "MainActivityPresenter"
 * <p>
 * Created by Lukasz Marczak
 * on 16.11.2016.
 */

public class Add implements Operation {
    @Override
    public double operate(double number1, double number2) {
        return number1+number2;
    }

    @Override
    public String operationSymbol() {
        return "+";
    }

}
