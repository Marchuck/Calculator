package pl.marczak.calculator;

import com.udojava.evalex.Expression;

import java.math.BigDecimal;

/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 27.12.2016.
 */

public class BusinessLogic {

    public static class Result {
        public final boolean success;
        public final String value;

        public Result(boolean success, String value) {
            this.success = success;
            this.value = value;
        }
    }

    public static Result getResult(String expression) {

        if (expression.isEmpty()) {
            return new Result(false, "Podaj wyrażenie do obliczenia!");
        }
        //library used for calculations:
        //  https://github.com/uklimaschewski/EvalEx
        Expression engine = new Expression(expression);
        BigDecimal wynik = null;
        try {
            wynik = engine.eval();
            engine.setPrecision(4);
        } catch (Exception x) {
            return new Result(false, x.getMessage());
        }

        if (wynik != null) {
            return new Result(true, wynik.toString());
        } else {
            return new Result(false, "Wystąpił błąd");
        }

    }
}
