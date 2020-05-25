package com.shydn.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class ProductFragment extends Fragment implements CategoryAdapter.OnCategoryListener {

    // RecyclerView recyclerView;
     private CategoryAdapter categoryAdapter;
     private FirebaseFirestore firebaseFirestore;
     private List<CategoryModel> categoryModelList;
     private CategoryAdapter.OnCategoryListener onCategoryListener;
     private RecyclerView recyclerView;
     private FirebaseAuth firebaseAuth;




    public ProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        onCategoryListener = this;

        firebaseFirestore =FirebaseFirestore.getInstance();
         recyclerView = view.findViewById(R.id.category_recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        firebaseFirestore.collection("products").orderBy("index").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()){

                        List<CategoryModel>categoryModelList = new ArrayList<>();
                        long no_of_category = (long)documentSnapshot.get("no_of_category");
                        for(long x=1;x<=no_of_category;x++){
                            categoryModelList.add(new CategoryModel(documentSnapshot.get("category_image_"+x).toString()
                            ,documentSnapshot.get("category_title_"+x).toString()));

                        }
                        categoryAdapter = new CategoryAdapter(categoryModelList,onCategoryListener);
                        recyclerView.setAdapter(categoryAdapter);
                        categoryAdapter.notifyDataSetChanged();

                    }


                } else {

                }

            }
        });
        /*
        List<CategoryModel>categoryModelList= new ArrayList<>();

        categoryModelList.add(new CategoryModel(R.drawable.ic_account_,"Saket"));
        categoryModelList.add(new CategoryModel(R.drawable.ic_about_us,"Muskan"));
        categoryModelList.add(new CategoryModel(R.drawable.ic_home,"pepsi"));
        categoryModelList.add(new CategoryModel(R.drawable.ic_logout,"Musdskan"));
        categoryModelList.add(new CategoryModel(R.drawable.ic_account_,"Saket"));
        categoryModelList.add(new CategoryModel(R.drawable.ic_about_us,"Muskan"));
        categoryModelList.add(new CategoryModel(R.drawable.ic_home,"Muskan"));
        categoryModelList.add(new CategoryModel(R.drawable.ic_logout,"Musdskan"));
        categoryAdapter = new CategoryAdapter(categoryModelList,this);
        recyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

         */



        return view;

    }
    /*
    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MyData",Context.MODE_PRIVATE);
        boolean a =sharedPreferences.getBoolean("login",false);
        if(a){
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);

        }



    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("MyData",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("login",false);
        editor.commit();
    }

     */



    @Override
    public void onCategoryClick(int position) {
        long position_of_category = position;
        Intent intent = new Intent(getActivity(),Productitem.class);
        intent.putExtra("POSITION_OF_CATEGORY",position_of_category);
        startActivity(intent);

    }
}
