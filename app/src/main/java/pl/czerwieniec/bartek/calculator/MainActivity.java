package pl.czerwieniec.bartek.calculator;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import pl.czerwieniec.bartek.calculator.calc.operations.Add;
import pl.czerwieniec.bartek.calculator.calc.Calculator;
import pl.czerwieniec.bartek.calculator.calc.operations.Divide;
import pl.czerwieniec.bartek.calculator.calc.operations.Multiply;
import pl.czerwieniec.bartek.calculator.calc.operations.Operation;
import pl.czerwieniec.bartek.calculator.calc.operations.Substract;

public class MainActivity extends AppCompatActivity implements UIConnector{

    Calculator calculator;
    EditText input;
    Vibrator vibrator;
    FloatingActionButton divideButton;
    FloatingActionButton addButton;
    FloatingActionButton multButton;
    FloatingActionButton substractButton;
    FloatingActionButton result;
    FloatingActionButton resetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        input = (EditText) findViewById(R.id.input_field);
        divideButton = (FloatingActionButton) findViewById(R.id.divide);
        addButton = (FloatingActionButton) findViewById(R.id.plus);
        multButton = (FloatingActionButton) findViewById(R.id.multiply);
        substractButton = (FloatingActionButton) findViewById(R.id.substract);
        result = (FloatingActionButton) findViewById(R.id.result);
        resetButton = (FloatingActionButton) findViewById(R.id.reset);

        //connect UI with business logic
        calculator = new Calculator(this);

        //clear listener
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.clear();
            }
        });

        //set Click listeners
        divideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentOperation(new Divide());
            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentOperation(new Add());
            }
        });
        multButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentOperation(new Multiply());
            }
        });
        substractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setCurrentOperation(new Substract());
            }
        });

        //result listener
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculator.calculateValue(currentText());
            }
        });
    }

    String currentText() {
        return input.getText().toString();
    }

    public void setCurrentOperation(Operation currentOperation) {

        String currentInput = currentText();

        //calculator.setNumber(currentInput);
        String newInput = currentInput +  currentOperation.operationSymbol();
        input.setText(newInput);
        //moves EditText cursor at the end of expression
        input.setSelection(input.getText().length());
    }

    /**
     * invoked when user divides by zero or other math error
     * @param error
     */
    @Override
    public void showError(String error) {
        Toast.makeText(MainActivity.this, error, Toast.LENGTH_SHORT).show();
        vibrator.vibrate(300);
    }

    /**
     *
     * @param result of calculation
     */
    @Override
    public void showResult(String result) {
        input.setText(result);
        //moves EditText cursor at the end of expression
        input.setSelection(result.length());
    }
}
