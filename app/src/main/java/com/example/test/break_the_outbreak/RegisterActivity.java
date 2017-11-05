package com.example.test.break_the_outbreak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Lillie Zhou on 11/4/17.
 */

public class RegisterActivity extends AppCompatActivity {
    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private User user;
    EditText name;
    EditText username;
    EditText password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        inputValidation = new InputValidation(RegisterActivity.this);
        databaseHelper = new DatabaseHelper(RegisterActivity.this);
        user = new User();
        name = (EditText) findViewById(R.id.editRegisterName);
        username = (EditText) findViewById(R.id.editRegisterUsername);
        password = (EditText) findViewById(R.id.editRegisterPassword);
        register = (Button) findViewById(R.id.registerButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate();
            }
        });
    }

    private void validate() {
        if (!inputValidation.isEditTextFilled(username, password)) {
            Toast.makeText(RegisterActivity.this, "Empty fields", Toast.LENGTH_SHORT).show();

         /*else if (!inputValidation.isEmailValid(email)) {
            Toast.makeText(RegisterActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();*/

        } else if (!databaseHelper.checkUser(username.getText().toString().trim(), password.getText().toString().trim())) {

            user.setName(name.getText().toString().trim());
            user.setUsername(username.getText().toString().trim());
            user.setPassword(password.getText().toString().trim());

            databaseHelper.addUser(user);
            Toast.makeText(RegisterActivity.this, "Added a new user!", Toast.LENGTH_SHORT).show();
            emptyInput();

            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(RegisterActivity.this, "User already exists", Toast.LENGTH_SHORT).show();
        }
    }
    private void emptyInput() {
        name.setText(null);
        username.setText(null);
        password.setText(null);
    }
}
