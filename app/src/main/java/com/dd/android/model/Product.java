package com.dd.android.model;

/**
 * Created by 57248 on 2016/8/26.
 */
public class Product {
    private int product_id;         //产品id
    private String name;    //名称
    private String disc;    //产品描述
    private Integer picId_fr;   //图片ID-first,second,third
    private Integer picId_se;
    private Integer picId_th;
    private double price;   //价格
    private String number;  //产品编号
    private String amount;  //规格
    private String note;    //备注
    private String type;    //产品类别

    public String getNumber() { return number;}

    public void setNumber(String number) {this.number = number;}

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPicId_fr() {
        return picId_fr;
    }

    public void setPicId_fr(Integer picId_fr) {
        this.picId_fr = picId_fr;
    }

    public Integer getPicId_th() {
        return picId_th;
    }

    public void setPicId_th(Integer picId_th) {
        this.picId_th = picId_th;
    }

    public Integer getPicId_se() {
        return picId_se;
    }

    public void setPicId_se(Integer picId_se) {
        this.picId_se = picId_se;
    }



    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }


}
