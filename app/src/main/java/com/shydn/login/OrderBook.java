package com.shydn.login;

public class OrderBook {
    private String title;
    private String model;
    private String price;
    private String quantity;
    private long timeStamp;

    public OrderBook() {
    }

    public OrderBook(String title, String model, String price, String quantity, long timeStamp) {
        this.title = title;
        this.model = model;
        this.price = price;
        this.quantity = quantity;
        this.timeStamp = timeStamp;
    }

    public String getTitle() {
        return title;
    }

    public String getModel() {
        return model;
    }

    public String getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }
    public long getTimeStamp(){
        return timeStamp;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
    public void setTimeStamp(long timeStamp){
        this.timeStamp = timeStamp;
    }
}
