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

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>{

    private List<CategoryModel>categoryModelList;
    private OnCategoryListener monCategoryListener;

    public CategoryAdapter(List<CategoryModel> categoryModelList,OnCategoryListener onCategoryListener) {
        this.categoryModelList = categoryModelList;
        this.monCategoryListener = onCategoryListener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
       return new CategoryViewHolder(view,monCategoryListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
      // CategoryModel categoryModel = categoryModelList.get(position);
        String resource = categoryModelList.get(position).getCategoryProductImage();
        String title = categoryModelList.get(position).getCategoryProductTitle();

       //holder.CategoryImage.setImageResource(categoryModel.getCategoryProductImage());
        Glide.with(holder.CategoryImage).load(categoryModelList.get(position).getCategoryProductImage()).apply(new RequestOptions().placeholder(R.drawable.ic_home_white)).into(holder.CategoryImage);
      // holder.CategoryTitle.setText(categoryModel.getCategoryProductTitle());
       holder.CategoryTitle.setText(title);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView CategoryImage;
        private TextView CategoryTitle;
        private OnCategoryListener onCategoryListener;
        public CategoryViewHolder(@NonNull View itemView,OnCategoryListener onCategoryListener) {
            super(itemView);
            CategoryImage = itemView.findViewById(R.id.category_image);
            CategoryTitle = itemView.findViewById(R.id.category_title);
            this.onCategoryListener = onCategoryListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onCategoryListener.onCategoryClick(getAdapterPosition());

        }
    }

    public interface OnCategoryListener{
        void onCategoryClick(int position);
    }

}
