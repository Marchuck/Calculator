package pl.marczak.calculator;

import android.widget.EditText;

/**
 * Project "Calculator"
 * <p>
 * Created by Lukasz Marczak
 * on 17.02.2017.
 */

public class UpdatableEditTextWrapper implements Updatable {

    EditText editText;

    public UpdatableEditTextWrapper(EditText editText) {
        this.editText = editText;
    }

    @Override
    public String getCurrentText() {
        if (editText == null) return "";
        return editText.getText().toString();
    }

    @Override
    public void setNewText(String newInput) {
        if (editText == null) return;
        editText.setText(newInput);
        editText.setSelection(editText.getText().length());
    }

}
