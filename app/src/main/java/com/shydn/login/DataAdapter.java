package com.shydn.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public  class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewholder> {

    private List<DataModel> dataModelList;
    private OnProductListener monProductListener;

    public DataAdapter(List<DataModel> dataModelList,OnProductListener onProductListener) {
        this.dataModelList = dataModelList;
        this.monProductListener = onProductListener;
    }

    public  class DataViewholder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView productImage;
        private TextView ProductTitle;
        private TextView ProductDescription;
        private TextView ProductPrice;
        public OnProductListener onProductListener;
        public itemClickListener listener;

        public DataViewholder(@NonNull View itemView,OnProductListener onProductListener) {
            super(itemView);
            productImage = itemView.findViewById(R.id.real_product_image);
            ProductTitle = itemView.findViewById(R.id.real_product_title);
            ProductDescription = itemView.findViewById(R.id.real_product_description);
            ProductPrice = itemView.findViewById(R.id.real_product_price);
            this.onProductListener=onProductListener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            onProductListener.onProductClick(getAdapterPosition());
        }





    }

    @NonNull
    @Override
    public DataViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View dataview = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.real_item_layout,viewGroup,false);
        return new DataViewholder(dataview,monProductListener);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewholder holder, int position) {
        String resource = dataModelList.get(position).getProductImage();
        String title = dataModelList.get(position).getProductTitle();
        String description = dataModelList.get(position).getProductDescription();
        String price = dataModelList.get(position).getProductPrice();

        Glide.with(holder.productImage).load(resource).apply(new RequestOptions().placeholder(R.drawable.ic_home_white)).into(holder.productImage);
        holder.ProductTitle.setText(title);
        holder.ProductDescription.setText(description);
        holder.ProductPrice.setText(price);

    }

    //@Override
   //// public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
      ///    List<DataModel> dataModelList1 = dataModelList.get(position).getDataModelList();
      //  ((DataViewholder)holder).setdatalist(dataModelList1);
   // }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public interface OnProductListener{
        void onProductClick(int position);
    }




    }
