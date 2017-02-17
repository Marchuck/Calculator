package pl.marczak.calculator;

import android.support.annotation.Nullable;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

import pl.marczak.calculator.MainActivityView;


/**
 * Project "MainActivityPresenter"
 * <p>
 * Created by Lukasz Marczak
 * on 16.11.2016.
 */

public class MainActivityPresenter {

    @Nullable
    MainActivityView connector;

    int precision = 4;

    public MainActivityPresenter(@Nullable MainActivityView connector) {
        this.connector = connector;
    }

    public void calculateValue(String s) {
        if (connector == null) return;
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
            expression.setPrecision(precision);
        } catch (Exception x) {

            connector.showError(x.getMessage());
        } finally {
            if (wynik != null) {
                connector.showResult(wynik.toString());
            }
        }
    }

    public void clear() {
        if (connector == null) return;
        connector.showResult("");
    }

    public void destroy() {
        connector = null;
    }
}
