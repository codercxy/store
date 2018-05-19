package com.dd.android.model;

/**
 * Created by 57248 on 2016/9/8.
 */
public class Constants {
    public static final int REACH = 200;
    public static final int CUT = 100;
    public static final double DISCOUNT = 0.8;

    public static final double getCount(double all) {
        return all - ((int)all / REACH) * CUT;
    }

    public static final int getDiscount(double all) {
        return ((int)all / REACH) * CUT;
    }

    public static  final int getBenefit(double all) {
        return (int)all - ((int)all / REACH) * CUT;
    }
}
