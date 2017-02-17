package pl.marczak.calculator;

/**
 * Project "MainActivityPresenter"
 * <p>
 * Created by Lukasz Marczak
 * on 16.11.2016.
 */

public interface MainActivityView {

    void showError(String error);

    void showResult(String result);
}
