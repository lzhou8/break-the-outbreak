package com.example.test.break_the_outbreak;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private int mYear;
    private int mMonth;
    private int mDay;

    private TextView mDateDisplay;
    private Button mPickDate;

    static final int DATE_DIALOG_ID = 0;

    private EditText name;
    private EditText symptoms;
    private EditText medications;
    private EditText locations;
    private EditText diet;
    private EditText additionalInfo;

    private Button sendTextButton;
    private Button sendEmailButton;
    private Button sendCDCButton;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.nameText);
        symptoms = findViewById(R.id.symptomsText);
        medications = findViewById(R.id.medication);
        locations = findViewById(R.id.locations);

        diet = findViewById(R.id.diet);
        additionalInfo = findViewById(R.id.additional);

        mDateDisplay = findViewById(R.id.showMyDate);
        mPickDate = findViewById(R.id.myDatePickerButton);

        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });

        // get the current date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // display the current date
        updateDisplay();

        sendTextButton = findViewById(R.id.textButton);
        sendTextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                sendText();
            }
        });

        sendEmailButton = findViewById(R.id.emailButton);
        sendEmailButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

        sendCDCButton = findViewById(R.id.cdcButton);
        sendCDCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "800-CDC-INFO"));
                String data = getData();

                smsIntent.putExtra("sms_body", data);
                startActivity(smsIntent);
            }
        });
    }

    protected void sendText() {
        // sends custom msg to phone number
        Intent smsIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + "4043332810"));
        String data = getData();

        smsIntent.putExtra("sms_body", data);
        startActivity(smsIntent);
    }

    protected String getData() {
        String data = "Hello, my name is " + name.getText().toString() + ". ";

        //data += "Illness started on " + startIllness.toString();

        String userSymptoms = symptoms.getText().toString();

        if (!userSymptoms.equals("Symptoms")) {
            data += "I am experiencing " + userSymptoms + ". ";
        } else {
            data += "No self reported symptoms.\n";
        }

        String userMedications = medications.getText().toString();
        if (!userMedications.equals("Current Medications")) {
            data += "I am currently taking " + userMedications + ". ";
        } else {
            data += "No self reported medications.\n";
        }

        String userDiet = diet.getText().toString();
        if (!userDiet.equals("Diet")) {
            data += "My diet consists of " + userDiet + ". ";
        } else {
            data += "No self reported dietary intake.\n";
        }

        String userLocations = locations.getText().toString();
        if (!userLocations.equals("Visited Locations")) {
            data += "Visited locations include: " + userLocations + ". ";
        } else {
            data += "No self reported locations.\n";
        }

        String userAdditional = additionalInfo.getText().toString();
        if (!userAdditional.equals("Additional Information")) {
            data += "Additional info: " + userAdditional;
        } else {
            data += "No other self reported info.";
        }
        return data;
    }

    protected void sendEmail() {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:" + email));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Patient Information");

        String data = getData();
        emailIntent.putExtra(Intent.EXTRA_TEXT, data);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "No email clients installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void updateDisplay() {
        this.mDateDisplay.setText(
                new StringBuilder()
                        // Month is 0 based so add 1
                        .append("Start of Illness: ")
                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    updateDisplay();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }
}
