package com.shydn.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class OrderAdapter extends FirestoreRecyclerAdapter<OrderBook, OrderAdapter.OrderViewHolder>{


    public OrderAdapter(@NonNull FirestoreRecyclerOptions<OrderBook> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull OrderViewHolder holder, int position, @NonNull OrderBook model) {
        holder.OrderTitle.setText(model.getTitle());
        holder.OrderModel.setText(model.getModel());
        holder.OrderPrice.setText(model.getPrice());
        holder.OrderQuantity.setText(model.getQuantity());
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_item_layout,parent,false);
        return new OrderViewHolder(view);
    }

    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder{
        private TextView OrderTitle;
        private TextView OrderPrice;
        private TextView OrderModel;
        private TextView OrderQuantity;
        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            OrderTitle = itemView.findViewById(R.id.order_product_title);
            OrderPrice = itemView.findViewById(R.id.order_product_price);
            OrderModel = itemView.findViewById(R.id.order_product_model);
            OrderQuantity = itemView.findViewById(R.id.order_product_Quantity);
        }
    }
}
