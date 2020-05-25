package com.shydn.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Productitem extends AppCompatActivity implements DataAdapter.OnProductListener {
    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth firebaseAuth;
    private DataAdapter dataAdapter;
    private List<DataModel> dataModelList;
    private long position_of_category;
    private DataAdapter.OnProductListener onProductListener;
    private RecyclerView recyclerView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.real_product_layout);
        firebaseFirestore = FirebaseFirestore.getInstance();
        onProductListener = this;
        Intent intent = getIntent();
        position_of_category = intent.getLongExtra("POSITION_OF_CATEGORY",0);
        Log.v("saket","this is.. "+position_of_category);

        recyclerView = findViewById(R.id.product_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        if(MainActivity.KEY_VERIFY) {
            firebaseFirestore.collection("product_item").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            if ((long) documentSnapshot.get("view_type") == position_of_category) {
                                List<DataModel> dataModelList = new ArrayList<>();
                                long no_of_products = (long) documentSnapshot.get("no_of_products");
                                for (long x = 1; x <= no_of_products; x++) {
                                    dataModelList.add(new DataModel(documentSnapshot.get("product_image_" + x).toString()
                                            , documentSnapshot.get("product_title_" + x).toString(), documentSnapshot.get("product_model_" + x).toString()
                                            , documentSnapshot.get("product_price_" + x).toString()));
                                }
                                getSupportActionBar().setTitle(documentSnapshot.get("heading").toString());
                                dataAdapter = new DataAdapter(dataModelList, onProductListener);
                                recyclerView.setAdapter(dataAdapter);
                                dataAdapter.notifyDataSetChanged();
                            }

                        }

                    } else {

                    }

                }
            });
        }  else{

            firebaseFirestore.collection("product_item").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                            if ((long) documentSnapshot.get("view_type") == position_of_category) {
                                List<DataModel> dataModelList = new ArrayList<>();
                                long no_of_products = (long) documentSnapshot.get("no_of_products");
                                for (long x = 1; x <= no_of_products; x++) {
                                    dataModelList.add(new DataModel(documentSnapshot.get("product_image_" + x).toString()
                                            , documentSnapshot.get("product_title_" + x).toString(), documentSnapshot.get("product_model_" + x).toString()
                                            ,""));
                                }
                                getSupportActionBar().setTitle(documentSnapshot.get("heading").toString());
                                dataAdapter = new DataAdapter(dataModelList, onProductListener);
                                recyclerView.setAdapter(dataAdapter);
                                dataAdapter.notifyDataSetChanged();
                            }

                        }

                    } else {

                    }

                }
            });

        }

        /*
        dataModelList.add(new DataModel(R.drawable.ic_home, "saket", "vvvv", "Rs.31000"));
        dataModelList.add(new DataModel(R.drawable.ic_logout, "Muskan", "aaaa", "Rs.31000"));
        dataModelList.add(new DataModel(R.drawable.ic_lrandom, "pepsi", "bbbbb", "Rs.32000"));
        dataModelList.add(new DataModel(R.drawable.ic_order, "aaaa", "ccccccc", "Rs.34000"));
        dataModelList.add(new DataModel(R.drawable.ic_home, "bbbb", "dddd", "Rs.35000"));
        dataModelList.add(new DataModel(R.drawable.ic_home, "cccc", "eeee", "Rs.36000"));
        dataAdapter = new DataAdapter(dataModelList,this);
        recyclerView.setAdapter(dataAdapter);
        dataAdapter.notifyDataSetChanged();
          */
    }






    @Override
    public void onProductClick(int position) {

   //    String product_title =  dataModelList.get(position).getProductTitle();
    //    String product_image = dataModelList.get(position).getProductImage();
    //    String product_description = dataModelList.get(position).getProductDescription();
    //    String product_price = dataModelList.get(position).getProductPrice();

     //   Intent intent = new Intent(this,ProductDetailsActivity.class);
    //    intent.putExtra("PRODUCT_TITLE",product_title);
     //   intent.putExtra("PRODUCT_IMAGE",product_image);
      //  intent.putExtra("PRODUCT_DESCRIPTION",product_description);
     //   intent.putExtra("PRODUCT_PRICE",product_price);
       // startActivity(intent);
        long position_of_category1 = position_of_category;
        long product_position = position;
        Intent intent = new Intent(this,ProductDetailsActivity.class);
        intent.putExtra("PRODUCT_POSITION",product_position);
        intent.putExtra("POSITION_OF_CATEGORY1",position_of_category1);
        startActivity(intent);
    }

}
