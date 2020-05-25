package com.shydn.login;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NavigationHeader extends AppCompatActivity {
    private TextView Header_name;
    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    private CollectionReference profileBookRef = db.collection("ProfileBook");
    private NavigationHeader header;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header);
        Header_name =(TextView)findViewById(R.id.nav_header_name_a);

        /*
        if(PhoneActivity.KEY_NUMBER == null){

        }else {
            setUpheaderName(PhoneActivity.KEY_NUMBER);
        }

         */
        String Cool = "cool";
        Header_name.setText(Cool);

    }
    /*
    public void setUpheaderName(String phonenumber){
        profileBookRef.document(phonenumber).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Header_name.setText(documentSnapshot.get("name_").toString());
            }
        });
    }

     */
}
