package com.shydn.login;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class GridProductLayoutAdapter extends BaseAdapter {

    List<ProductScrollModel> productScrollModelList;

    public GridProductLayoutAdapter(List<ProductScrollModel> productScrollModelList) {
        this.productScrollModelList = productScrollModelList;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1;
        if(view == null){
           view1 = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.scroll_item_layout,null);
           view1.setElevation(0);
           view1.setBackgroundColor(Color.parseColor("#FFFFFF"));
            ImageView productImage =  view1.findViewById(R.id.data_product_image);
            TextView productName = view1.findViewById(R.id.data_product_name);
            TextView productDesc = view1.findViewById(R.id.data_product_desc);
            TextView productPrice = view1.findViewById(R.id.data_product_price);

            Glide.with(productImage.getContext()).load(productScrollModelList.get(i).getProductImage()).apply(new RequestOptions().placeholder(R.drawable.ic_home_white)).into(productImage);
            productName.setText(productScrollModelList.get(i).getProductTitle());
            productDesc.setText(productScrollModelList.get(i).getProductDescription());
            productPrice.setText(productScrollModelList.get(i).getProductPrice());

        }else {
            view1 = view;

        }
        return view1;
    }
}
