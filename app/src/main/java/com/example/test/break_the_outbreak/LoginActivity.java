package com.example.test.break_the_outbreak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by test on 11/4/17.
 */

public class LoginActivity extends AppCompatActivity {
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    EditText username;
    EditText password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inputValidation = new InputValidation(LoginActivity.this);
        databaseHelper = new DatabaseHelper(LoginActivity.this);

        username = (EditText) findViewById(R.id.inputUsername);
        password = (EditText) findViewById(R.id.inputPassword);
        login = (Button) findViewById(R.id.loginButton);

        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void validate() {
        if (!inputValidation.isEditTextFilled(username, password)) {
            Toast.makeText(LoginActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();

        } else if (databaseHelper.checkUser(username.getText().toString().trim(), password.getText().toString().trim())) {

            Toast.makeText(LoginActivity.this, "Successful login!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            intent.putExtra("user-name", username.getText().toString().trim());

            emptyInput();
            startActivity(intent);

        } else {
            Toast.makeText(LoginActivity.this, "Unsuccessful login!", Toast.LENGTH_SHORT).show();
        }
    }

    private void emptyInput() {
        username.setText(null);
        password.setText(null);
    }
}
