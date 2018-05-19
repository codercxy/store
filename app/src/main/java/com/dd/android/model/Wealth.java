package com.dd.android.model;

/**
 * Created by 57248 on 2016/9/7.
 */
public class Wealth {
    private int coin;
    private double balance;
    private int benefit;
    private int discount_type;

    public int getCoin() {
        return coin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getBenefit() {
        return benefit;
    }

    public void setBenefit(int benefit) {
        this.benefit = benefit;
    }

    public int getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(int discount_type) {
        this.discount_type = discount_type;
    }

    public void setCoin(int coin) {
        this.coin = coin;

    }


}
