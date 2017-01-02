package pl.marczak.calculator.calc;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

import pl.marczak.calculator.UIConnector;


/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 16.11.2016.
 */

public class Calculator {

    final UIConnector connector;

    public Calculator(UIConnector connector) {
        this.connector = connector;
    }

    public void calculateValue(String s) {
        if (s.isEmpty()) {
            connector.showError("Podaj wyrażenie do obliczenia!");
            return;
        }
        //library used for calculations:
        //  https://github.com/uklimaschewski/EvalEx
        Expression expression = new Expression(s);
        BigDecimal wynik = null;
        try {
            wynik = expression.eval();
            expression.setPrecision(4);
        } catch (Exception x) {
            connector.showError(x.getMessage());
        } finally {
            if (wynik != null) {
                connector.showResult(wynik.toString());
            }
        }
    }

    public void clear() {
        connector.showResult("");
    }
}
