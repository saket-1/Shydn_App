package com.shydn.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class OrderFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference orderBookRef = db.collection("OrderBook");
    private OrderAdapter orderAdapter;


    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);



     setUpRecyclerView(view);

        return view;
    }

    private void setUpRecyclerView(View view){
        if(PhoneActivity.KEY_NUMBER==null){
            Toast.makeText(getActivity(), "Please login to see order list", Toast.LENGTH_SHORT).show();
        } else {
            Query query = orderBookRef.document(PhoneActivity.KEY_NUMBER).collection(PhoneActivity.KEY_NUMBER).orderBy("timeStamp", Query.Direction.ASCENDING);
            FirestoreRecyclerOptions<OrderBook> options = new FirestoreRecyclerOptions.Builder<OrderBook>()
                    .setQuery(query, OrderBook.class)
                    .build();
            orderAdapter = new OrderAdapter(options);
            RecyclerView order_recycler = view.findViewById(R.id.order_recycler);
            order_recycler.setHasFixedSize(true);
            order_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
            order_recycler.setAdapter(orderAdapter);

            new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    orderAdapter.deleteItem(viewHolder.getAdapterPosition());
                }
            }).attachToRecyclerView(order_recycler);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        if(PhoneActivity.KEY_NUMBER == null){

        }else {
            orderAdapter.startListening();
        }
    }


    @Override
    public void onStop() {
        super.onStop();
        if(PhoneActivity.KEY_NUMBER == null){

        } else {
            orderAdapter.stopListening();
        }
    }
}
