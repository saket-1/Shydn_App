package com.shydn.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
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

import java.util.Objects;

public class PhoneActivity extends AppCompatActivity {



    private Spinner spinner;
    private EditText editText;
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    private CollectionReference profileBookRef = db.collection("ProfileBook");
    private ListenerRegistration profileListener;
    //public static boolean KEY_Check_Number;
    //public static boolean KEY_VERIFY;
    public static String KEY_NUMBER;
    public static boolean KEY_VERIFY;
    public static boolean KEY_Check_Number;
    public  final static String TAG ="PhoneActivity";
    private WebView mWebView;


    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_phone);



        spinner = findViewById(R.id.spinnerCountries_login);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        editText = findViewById(R.id.editTextPhone_login);

        findViewById(R.id.buttonContinue_login).setOnClickListener(new View.OnClickListener() {
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
                KEY_NUMBER = phoneNumber;
                checkVerification(phoneNumber);
                }


        });
    }
    public void register(View v){
        Intent intent = new Intent(PhoneActivity.this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }


    public void checkVerification(final String phoneNumber){


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

                    if (Objects.equals(documentSnapshot.get("verification"), true)) {

                        if(ProfileActivity.KEY_DEVICE) {
                            Log.v("came", "Verified" + documentSnapshot.get("verification"));
                            Log.d(TAG, "onEvent: " + source);
                            KEY_VERIFY = true;
                            profileListener.remove();
                            Intent intent = new Intent(PhoneActivity.this, MainActivity.class);
                            intent.putExtra("verify", true);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Toast.makeText(PhoneActivity.this, "You can log in with one device only", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(PhoneActivity.this, "Thank you for Registration.Your Account will soon be verified", Toast.LENGTH_SHORT).show();
                        KEY_VERIFY = false;
                    }
                }   else {
                    Toast.makeText(PhoneActivity.this, "Your Number is not Registered.Please Register first", Toast.LENGTH_SHORT).show();
                    KEY_VERIFY = false;
                }
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(PhoneActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }
}
