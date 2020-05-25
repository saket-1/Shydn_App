package com.shydn.login;

public class ProductScrollModel {
    private String productImage;
    private String ProductTitle;
    private String ProductDescription;
    private String ProductPrice;

    public ProductScrollModel(String productImage, String productTitle, String productDescription, String productPrice) {
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
        ProductTitle = productTitle;
    }

    public String getProductDescription() {
        return ProductDescription;
    }

    public void setProductDescription(String productDescription) {
        ProductDescription = productDescription;
    }

    public String getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(String productPrice) {
        ProductPrice = productPrice;
    }
}
