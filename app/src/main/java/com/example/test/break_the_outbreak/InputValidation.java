package com.example.test.break_the_outbreak;

import android.content.Context;
import android.widget.EditText;

/**
 * Created by test on 11/4/17.
 */

public class InputValidation {

    private Context context;

    public InputValidation(Context context) {
        this.context = context;
    }

    public boolean isEditTextFilled(EditText textInput1, EditText textInput2) {

        String value1 = textInput1.getText().toString().trim();
        String value2 = textInput2.getText().toString().trim();

        if (value1.isEmpty()) {
            return false;
        } else if (value2.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     public boolean isEditTextValid(EditText textInput1, EditText textInput2) {
     String value1 = textInput1.getText().toString().trim();
     String value2 = textInput2.getText().toString().trim();
     if (!value1.contentEquals(value2)) {
     return false;
     }
     return true;
     }*/

    /*
    public boolean isEmailValid(EditText textInput) {
        String value = textInput.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            return false;
        }
        return true;
    }*/
}
