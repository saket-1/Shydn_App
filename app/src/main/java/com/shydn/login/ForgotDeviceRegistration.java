package com.shydn.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;



public class ForgotDeviceRegistration extends AppCompatActivity {
    private EditText editText;
    private Spinner spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot_password);
        spinner = findViewById(R.id.spinnerCountries_forgot);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        editText = findViewById(R.id.editTextPhone_forgot);

        findViewById(R.id.buttonContinue_forgot).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String number = editText.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }

                 String phoneNumber = "+" + code + number;
                Intent intent = new Intent(ForgotDeviceRegistration.this,ForgotVerifyActivity.class);
                intent.putExtra("phonenumber",phoneNumber);
                startActivity(intent);
                finish();


            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ForgotDeviceRegistration.this,RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}

