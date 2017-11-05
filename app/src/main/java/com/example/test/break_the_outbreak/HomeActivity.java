package com.example.test.break_the_outbreak;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by test on 11/4/17.
 */

public class HomeActivity extends AppCompatActivity {

    TextView welcomeUser;
    Button fileReport;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        welcomeUser = findViewById(R.id.welcomeText);
        welcomeUser.setText("Welcome " + getIntent().getExtras().getString("user-name"));

        logout = findViewById(R.id.logoutButton);
        logout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

        fileReport = findViewById(R.id.fileReportButton);
        fileReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

}
