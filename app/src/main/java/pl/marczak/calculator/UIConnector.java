package pl.marczak.calculator;

/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 16.11.2016.
 */

public interface UIConnector {

    void showError(String error);

    void showResult(String result);
}
