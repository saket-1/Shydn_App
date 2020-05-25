package com.shydn.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    private TextInputEditText name;
    private TextInputEditText lastname;
    private TextInputEditText shopName;
    private TextInputEditText panNo;
    private TextInputEditText address;
    private TextInputEditText city;
    private TextInputEditText district;
    private TextView mobileNumber;
    private TextInputEditText telephone;
    private TextInputEditText whatsApp;
    private Button register;
    public String name_;
    public String last_name;
    public String shop_name;
    public String pan_no;
    public String address_;
    public String city_;
    public String district_;
    public String mobile_number;
    public String telephone_;
    public String whats_App;
    public Boolean verification;
    public static  boolean KEY_DEVICE;

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference profileBookRef = db.collection("ProfileBook");
    private DocumentReference profileRef;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_profile);

        name = findViewById(R.id.profile_name);
        lastname = findViewById(R.id.profile_last_name);
        shopName = findViewById(R.id.profile_shop_name_textView);
        panNo = findViewById(R.id.pan_no_profile);
        address = findViewById(R.id.address_profile);
        city = findViewById(R.id.city_profile);
        district = findViewById(R.id.district_profile);
        mobileNumber = findViewById(R.id.mobile_num_profile);
        telephone = findViewById(R.id.telephone_profile);
        whatsApp = findViewById(R.id.whatsapp_profile);
        register = findViewById(R.id.save_profile);

        mobileNumber.setText(RegisterActivity.KEY_NUMBER_REGISTER);






/*
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(name_.isEmpty()||last_name.isEmpty()){
                    name.setError("Please Enter name");
                    lastname.setError("Please Enter last name");
                }
            }
        });

         */

    }


    public void addProfile(View v){
        name_=name.getText().toString();
        last_name = lastname.getText().toString();
        shop_name = shopName.getText().toString();
        pan_no = panNo.getText().toString();
        address_ = address.getText().toString();
        city_ = city.getText().toString();
        district_ = district.getText().toString();
        mobile_number = mobileNumber.getText().toString();
        telephone_ = telephone.getText().toString();
        whats_App = whatsApp.getText().toString();
        verification = false;
        Log.v("agrawal","names is:"+name_);
        ProfileBook profileBook = new ProfileBook(name_,last_name,shop_name,pan_no,address_,city_,district_,mobile_number,telephone_,whats_App,verification);
          profileBookRef.document(mobile_number).set(profileBook)
                  .addOnSuccessListener(new OnSuccessListener<Void>() {
                      @Override
                      public void onSuccess(Void aVoid) {
                         KEY_DEVICE = true;
                          Toast.makeText(ProfileActivity.this, "Profile created Successfully", Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(ProfileActivity.this,PhoneActivity.class);
                          startActivity(intent);
                          finish();
                      }
                  })
                  .addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                          Toast.makeText(ProfileActivity.this, "Profile Could not be Created", Toast.LENGTH_SHORT).show();
                      }
                  });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfileActivity.this,RegisterActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
