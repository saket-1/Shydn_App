package com.shydn.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextInputEditText mloginemail;
    private TextInputEditText mloginPassword;
    private Button mLoginbtn;
    private ProgressDialog progressDialog;
    private TextView login;
    private TextView forgot;
    private FirebaseAuth mauth;
    private TextView mobileNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);


        mauth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        mloginemail = (TextInputEditText) findViewById(R.id.login_email);
        mloginPassword = (TextInputEditText) findViewById(R.id.login_password);
        mLoginbtn = (Button) findViewById(R.id.login_btn);
        //login = (TextView) findViewById(R.id.login_text);
        //forgot = (TextView) findViewById(R.id.forgot_text);
        mobileNumber = (TextView)findViewById(R.id.mobile_num_login);


        mLoginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mloginemail.getText().toString();
                String password = mloginPassword.getText().toString();
                if ((!TextUtils.isEmpty(email)) || !TextUtils.isEmpty(password)) {
                    progressDialog.setTitle("Logging in");
                    progressDialog.setMessage("Please wait..");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    loginuser(email, password);
                }
            }
        });
        /*
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,ForgotPasswordActivity.class);
                startActivity(intent);
                finish();
            }
        });

         */





    }

        private void loginuser(String email, String password){
        mauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    SharedPreferences sharedPreferences = getSharedPreferences("MyData",MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean("verifyemail",true);
                    editor.apply();
                    progressDialog.dismiss();
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    Log.v("saket", "Working up to here : Email Running" );
                    finish();

                }
                else{
                    progressDialog.hide();
                    Toast.makeText(LoginActivity.this,"Try again",Toast.LENGTH_LONG).show();
                }

            }
        });

        }

}
