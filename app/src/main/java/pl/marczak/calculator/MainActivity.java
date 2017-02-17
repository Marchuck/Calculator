package pl.marczak.calculator;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.fabric.sdk.android.Fabric;
import pl.czerwieniec.bartek.calculator.BuildConfig;
import pl.czerwieniec.bartek.calculator.R;
import pl.marczak.calculator.calc.operations.Add;
import pl.marczak.calculator.calc.operations.Divide;
import pl.marczak.calculator.calc.operations.Multiply;
import pl.marczak.calculator.calc.operations.Operation;
import pl.marczak.calculator.calc.operations.Substract;

public class MainActivity extends AppCompatActivity implements MainActivityView {

    MainActivityPresenter calculator;

    Updatable inputWrapper;

    Vibrator vibrator;

    int vibrationTime = 300;

    @BindView(R.id.textView)
    TextView title;

    @OnClick(R.id.divide)
    void onDivide() {
        setCurrentOperation(new Divide());
    }

    @OnClick(R.id.plus)
    void onAdd() {
        setCurrentOperation(new Add());
    }

    @OnClick(R.id.multiply)
    void onMulitply() {
        setCurrentOperation(new Multiply());
    }

    @OnClick(R.id.substract)
    void onSubstract() {
        setCurrentOperation(new Substract());
    }

    @OnClick(R.id.reset)
    void onReset() {
        if (calculator != null) calculator.clear();
    }

    @OnClick(R.id.result)
    void onResult() {
        if (calculator != null) {
            String currentText = inputWrapper.getCurrentText();
            calculator.calculateValue(currentText);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fabric.with(this, new Crashlytics());
        ButterKnife.bind(this);

        inputWrapper = new UpdatableEditTextWrapper(
                (EditText) findViewById(R.id.input_field)
        );

        calculator = new MainActivityPresenter(this);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        title.setText(BuildConfig.FLAVOR.toUpperCase().concat(" Calculator"));
    }

    public void setCurrentOperation(Operation currentOperation) {

        String currentInput = inputWrapper.getCurrentText();
        String newInput = currentInput.concat(currentOperation.operationSymbol());
        inputWrapper.setNewText(newInput);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
        vibrator.vibrate(vibrationTime);
    }

    @Override
    public void showResult(String result) {
        inputWrapper.setNewText(result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        calculator.destroy();
        calculator = null;
    }
}
