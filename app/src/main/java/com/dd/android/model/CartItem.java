package com.dd.android.model;

/**
 * Created by 57248 on 2016/8/22.
 */
public class CartItem {
    private int picId;
    private String title;
    private String disc;
    private double price;
    private int num;
    private double count;

    public double getCount() {
        return count;
    }

    public void setCount(double count) {
        this.count = count;
    }



    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


}
