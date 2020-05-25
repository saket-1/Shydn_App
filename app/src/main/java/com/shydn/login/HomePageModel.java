package com.shydn.login;

import java.util.List;

public class HomePageModel {
    public  static  final int BANNER_SLIDER = 0;
    public  static  final int GRID_PRODUCT_VIEW = 1;
    public static  final int DATA_List = 2;
    private  int type;
    ////Banner Slider
    private List<Slider_model> sliderModelList;

    public HomePageModel(int type, List<Slider_model> sliderModelList) {
        this.type = type;
        this.sliderModelList = sliderModelList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<Slider_model> getSliderModelList() {
        return sliderModelList;
    }

    public void setSliderModelList(List<Slider_model> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
    ////Banner Slider
    /////Data List//


    ////Grid Product
    private String title;
    private List<ProductScrollModel> productScrollModelList;

    public HomePageModel(int type, String title, List<ProductScrollModel> productScrollModelList) {
        this.type = type;
        this.title = title;
        this.productScrollModelList = productScrollModelList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProductScrollModel> getProductScrollModelList() {
        return productScrollModelList;
    }

    public void setProductScrollModelList(List<ProductScrollModel> productScrollModelList) {
        this.productScrollModelList = productScrollModelList;
    }
////Grid Product

}
