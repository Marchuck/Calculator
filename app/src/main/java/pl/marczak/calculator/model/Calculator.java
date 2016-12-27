package pl.marczak.calculator.model;

/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 27.12.2016.
 */

public class Calculator {
    public String currentExpression;
    public String currentOperation;
    public boolean isError;
    public String  errorMessage;

    public Calculator(String currentExpression, String currentOperation, boolean isError, String errorMessage) {
        this.currentExpression = currentExpression;
        this.currentOperation = currentOperation;
        this.isError = isError;
        this.errorMessage = errorMessage;
    }
}
