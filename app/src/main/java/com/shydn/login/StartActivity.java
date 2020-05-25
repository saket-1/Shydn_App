package com.shydn.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;



import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {
    private Button regbtn;
    private Button loginbtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getSupportActionBar().hide();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        regbtn =(Button) findViewById(R.id.reg_startbtn);
        loginbtn = (Button) findViewById(R.id.login_startbtn);
        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent reg = new Intent(StartActivity.this,RegisterActivity.class);
                startActivity(reg);
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abc = new Intent(StartActivity.this,LoginActivity.class);
                startActivity(abc);
            }
        });

    }
}
