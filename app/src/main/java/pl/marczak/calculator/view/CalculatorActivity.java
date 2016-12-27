package pl.marczak.calculator.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import pl.marczak.calculator.model.BusinessLogic;
import pl.marczak.calculator.R;


public class CalculatorActivity extends AppCompatActivity {

    public static final String TAG = CalculatorActivity.class.getSimpleName();
    TextView currentMessage;
    RxEditText input_field;
    public Subject<Boolean> getResultSubject = BehaviorSubject.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        injectViews();
        Observable.combineLatest(getResultSubject, input_field.asObservable(),
                new BiFunction<Boolean, CharSequence, Boolean>() {
                    @Override
                    public Boolean apply(Boolean wantResults, CharSequence currentInput) throws Exception {
                        Log.i(TAG, "apply: " + wantResults + ", " + currentInput);
                        if (!wantResults) {
                            return false;
                        }
                        getResultSubject.onNext(false);

                        BusinessLogic.Result result = BusinessLogic.getResult(currentInput);

                        currentMessage.setText(result.success ? "" : result.value);
                        input_field.setText(!result.success ? "" : result.value);
                        return false;
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

    private void injectViews() {

        findViewById(R.id.substract).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_field.setText(input_field.getText() + "-");
            }
        });
        findViewById(R.id.multiply).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_field.setText(input_field.getText() + "*");
            }
        });
        findViewById(R.id.divide).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_field.setText(input_field.getText() + "/");
            }
        });
        findViewById(R.id.plus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_field.setText(input_field.getText() + "+");
            }
        });
        findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_field.setText("");
            }
        });
        findViewById(R.id.result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getResultSubject.onNext(true);
            }
        });

        input_field = (RxEditText) findViewById(R.id.input_field);
        currentMessage = (TextView) findViewById(R.id.currentMessage);

    }





}
