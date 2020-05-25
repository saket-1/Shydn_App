package com.shydn.login;

public class CategoryModel {
    private String categoryProductImage;
    private String categoryProductTitle;

    public CategoryModel(String categoryProductImage, String categoryProductTitle) {
        this.categoryProductImage = categoryProductImage;
        this.categoryProductTitle = categoryProductTitle;
    }

    public String getCategoryProductImage() {
        return categoryProductImage;
    }

    public void setCategoryProductImage(String categoryProductImage) {
        this.categoryProductImage = categoryProductImage;
    }

    public String getCategoryProductTitle() {
        return categoryProductTitle;
    }

    public void setCategoryProductTitle(String categoryProductTitle) {
        this.categoryProductTitle = categoryProductTitle;
    }
}
