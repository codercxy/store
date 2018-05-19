package com.dd.android.provider;

import android.net.Uri;

/**
 * Created by 57248 on 2016/9/7.
 */
public class DbWealth {
    public static final String PROVIDER_NAME = "com.dd.android.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/"
            + Wealth.TABLE_NAME);

    public final static String DB_NAME = "ddwine.db";
    public final static int DB_VERSION = 1;

    public class Wealth {
        public static final String TABLE_NAME = "wealth";
        public static final String COIN = "coin";
        public static final String BALANCE = "balance";
        public static final String BENEFIT = "benefit";
        public static final String DISCOUNT_TYPE = "discount_type";

        public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                COIN + " INTEGER," +
                BALANCE + " REAL," +
                BENEFIT + " INTEGER," +
                DISCOUNT_TYPE + " INTEGER);";


    }
}
