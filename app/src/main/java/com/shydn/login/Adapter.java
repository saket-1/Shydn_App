package com.shydn.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;



public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<String> ntitles;
    List<Integer> images;
    LayoutInflater inflater;

    public Adapter(Context ctx, List<String>titles, List<Integer> images){
        this.ntitles = titles;
        this.images = images;
        this.inflater = LayoutInflater.from(ctx);




    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_grid_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.title.setText(ntitles.get(position));
            holder.imageView.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.data_product_price);
            imageView = itemView.findViewById(R.id.data_product_desc);
        }
    }
}
