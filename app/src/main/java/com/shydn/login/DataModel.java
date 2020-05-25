package com.shydn.login;

import java.util.List;

public class DataModel {
    private String productImage;
    private String ProductTitle;
    private String ProductDescription;
    private String ProductPrice;

    public DataModel(String productImage, String productTitle, String productDescription, String productPrice) {
        this.productImage = productImage;
        this.ProductTitle = productTitle;
        this.ProductDescription = productDescription;
        this.ProductPrice = productPrice;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductTitle() {
        return ProductTitle;
    }

    public void setProductTitle(String productTitle) {
        this.ProductTitle = productTitle;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        this.ProductDescription = productDescription;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        this.ProductPrice = productPrice;
    }
}
