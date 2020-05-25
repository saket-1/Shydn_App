package com.shydn.login;

public class ProfileBook {

    private String name_;
    private String last_name;
    private String shop_name;
    private String pan_no;
    private String address_;
    private String city_;
    private String district_;
    private String mobile_number;
    private String telephone_;
    private String whats_App;
    private Boolean verification;

    public ProfileBook(String name_, String last_name, String shop_name, String pan_no, String address_, String city_, String district_, String mobile_number, String telephone_, String whats_App, Boolean verification) {
        this.name_ = name_;
        this.last_name = last_name;
        this.shop_name = shop_name;
        this.pan_no = pan_no;
        this.address_ = address_;
        this.city_ = city_;
        this.district_ = district_;
        this.mobile_number = mobile_number;
        this.telephone_ = telephone_;
        this.whats_App = whats_App;
        this.verification = verification;
    }


    public String getName_(){
        return name_;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public String getPan_no() {
        return pan_no;
    }

    public String getAddress_() {
        return address_;
    }

    public String getCity_() {
        return city_;
    }

    public String getDistrict_() {
        return district_;
    }

    public String getMobile_number() {
        return mobile_number;
    }

    public String getTelephone_() {
        return telephone_;
    }

    public String getWhats_App() {
        return whats_App;
    }
    public Boolean getVerification() {
        return verification;
    }
}
