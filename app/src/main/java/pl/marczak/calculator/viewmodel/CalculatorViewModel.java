package pl.marczak.calculator.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import pl.marczak.calculator.model.BusinessLogic;

import static pl.marczak.calculator.RxUtils.toObservable;


/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 27.12.2016.
 */

public class CalculatorViewModel {

    public static final String TAG = CalculatorViewModel.class.getSimpleName();

    public ObservableField<String> input_field = new ObservableField<>("");
    public ObservableField<String> currentMessage = new ObservableField<>("");

    public Subject<Boolean> getResultSubject = BehaviorSubject.create();

    public CalculatorViewModel() {
        getResultSubject.onNext(false);

        Observable.zip(getResultSubject, toObservable(input_field),
                new BiFunction<Boolean, String, Boolean>() {
                    @Override
                    public Boolean apply(Boolean wantResults, String currentInput) throws Exception {
                        Log.i(TAG, "apply: " + wantResults + ", " + currentInput);
                        if (!wantResults) {
                            return false;
                        }

                        BusinessLogic.Result result = BusinessLogic.getResult(currentInput);

                        if (result.success) {
                            currentMessage.set("");
                            input_field.set(result.value);
                        } else {
                            currentMessage.set(result.value);
                            input_field.set("");
                        }
                        currentMessage.notifyChange();
                        input_field.notifyChange();

                        return true;
                    }
                })

                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Exception {
                        Log.i(TAG, "onNext: " + aBoolean);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "error: ", throwable);
                    }
                });

    }

    public void getResult() {
        getResultSubject.onNext(true);
    }

    public void add() {

        input_field.set(input_field.get() + "+");
        input_field.notifyChange();
    }

    public void substract() {
        input_field.set(input_field.get() + "-");
        input_field.notifyChange();
    }

    public void divide() {
        input_field.set(input_field.get() + "/");
        input_field.notifyChange();
    }

    public void multiply() {
        input_field.set(input_field.get() + "*");
        input_field.notifyChange();
    }

    public void clear() {
        input_field.set("");
        input_field.notifyChange();
    }

}
