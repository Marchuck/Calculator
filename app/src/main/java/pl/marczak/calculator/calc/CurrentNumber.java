package pl.marczak.calculator.calc;

/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 16.11.2016.
 */

public class CurrentNumber {

    double value;

    boolean isValueSet;

    public void set(double number) {

        this.value = number;
        isValueSet = true;
    }

    public void clear() {
        isValueSet = false;
    }

    public boolean isSet() {
        return isValueSet;
    }
}
