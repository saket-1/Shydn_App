package com.shydn.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;



public class TimeActivity extends AppCompatActivity {
    Thread thread;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);//will hide the title
        getSupportActionBar().hide(); //hide the title bar
        setContentView(R.layout.activity_time);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN); //show the activity in full screen

        thread = new Thread() {
            @Override
            public void run() {
                try {
                        Thread.sleep(3000);
                        runOnUiThread(new Runnable() {
                                          @Override
                                          public void run() {

                                              Intent intent = new Intent(TimeActivity.this,MainActivity.class);
                                              startActivity(intent);
                                              finish();
                                          }
                                      });
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }


    }







