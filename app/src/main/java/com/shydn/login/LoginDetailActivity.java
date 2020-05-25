package com.shydn.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class LoginDetailActivity extends AppCompatActivity {

    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    private CollectionReference profileBookRef = db.collection("ProfileBook");
    private ListenerRegistration profileListener;
    public static boolean KEY_VERIFY;
    public static boolean KEY_Check_Number;
    public boolean a;
    public boolean b;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_login);


        Log.v("checker","verify is"+KEY_VERIFY);
        Log.v("checker","check is"+KEY_Check_Number);

          String s ="+919521206203";
        checkVerification(s);
    }

    @Override
    protected void onStart() {
        super.onStart();
       // String s = "+919521206205";
        //checkNumber(s);
        //checkVerification(s);

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

                // assert queryDocumentSnapshots != null;
                if(documentSnapshot.exists()) {

                    if (Objects.equals(documentSnapshot.getMetadata().hasPendingWrites(), true)) {
                        KEY_VERIFY = true;
                        Log.v("came", "Verified"+documentSnapshot.get("verification"));
                        Intent intent = new Intent(LoginDetailActivity.this,MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else {
                        Toast.makeText(LoginDetailActivity.this, "Thank you for Registration.Your Number will soon be verified", Toast.LENGTH_SHORT).show();

                        KEY_VERIFY = false;
                    }
                }   else {
                    Toast.makeText(LoginDetailActivity.this, "Your Number is not registered.please register first", Toast.LENGTH_SHORT).show();
                    KEY_VERIFY = false;
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        profileListener.remove();
    }
}
