package pl.marczak.calculator.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import pl.marczak.calculator.R;
import pl.marczak.calculator.databinding.ActivityMainBinding;
import pl.marczak.calculator.viewmodel.CalculatorViewModel;


public class CalculatorActivity extends AppCompatActivity {

//    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        binding.setViewModel(new CalculatorViewModel());

        EditText editText = (EditText) findViewById(R.id.input_field);

    }
}
