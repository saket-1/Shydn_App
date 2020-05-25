package com.shydn.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    /*
    private EditText forgotEmail;
    private Button ResetBtn;
    private  ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_forgot_password);
        firebaseAuth = FirebaseAuth.getInstance();
        forgotEmail = (EditText)findViewById(R.id.reg_Forgot);
        ResetBtn = (Button)findViewById(R.id.reset_button);
        progressDialog = new ProgressDialog(this);

        ResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = forgotEmail.getText().toString();
                if(!TextUtils.isEmpty(email)){
                    progressDialog.setTitle("Resetting the password");
                    progressDialog.setMessage("Please wait..");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    Reset(email);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    private void Reset(String email){
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Intent intent = new Intent(ForgotPasswordActivity.this,LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    progressDialog.hide();
                    Toast.makeText(ForgotPasswordActivity.this,"Registered Email_Id not Found",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

     */

}
