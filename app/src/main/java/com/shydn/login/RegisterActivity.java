package com.shydn.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;




import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;

public class RegisterActivity extends AppCompatActivity {

    private Spinner spinner;
    public  boolean a;
    private EditText editText;
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    private CollectionReference profileBookRef = db.collection("ProfileBook");
    private ListenerRegistration profileListener;
    public static String KEY_NUMBER_REGISTER;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = findViewById(R.id.spinnerCountries_register);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        editText = findViewById(R.id.editTextPhone_register);

        findViewById(R.id.buttonContinue_register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String number = editText.getText().toString().trim();

                if (number.isEmpty() || number.length() < 10) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }

                final String phoneNumber = "+" + code + number;
                KEY_NUMBER_REGISTER = phoneNumber;
                CheckNumberRegister(phoneNumber);

            }
        });
    }
    public void newDevice(View view){
        Intent intent = new Intent(RegisterActivity.this,ForgotDeviceRegistration.class);
        startActivity(intent);
        finish();
    }
    public void CheckNumberRegister(String phoneNumber){

        profileListener =  profileBookRef.document(phoneNumber).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if(e!= null)
                {
                    return;
                }
                String source = documentSnapshot != null && documentSnapshot.getMetadata().hasPendingWrites()
                        ? "Local" : "Server";
                if(documentSnapshot.exists()) {
                    Toast.makeText(RegisterActivity.this, "This Number is already registered", Toast.LENGTH_SHORT).show();
                }   else {
                    Intent intent = new Intent(RegisterActivity.this,VerifyPhoneActivity.class);
                    intent.putExtra("phonenumber", KEY_NUMBER_REGISTER);
                    startActivity(intent);
                    finish();

                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, PhoneActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


    }
}
