package com.dd.android.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by 57248 on 2016/9/5.
 */
public class DbOrders {
    public static final String PROVIDER_NAME = "com.dd.android.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/" +
            Order.TABLE_NAME);

    public final static String DB_NAME = "ddwine.db";
    public final static int DB_VERSION = 1;

    public class Order {
        public static final String TABLE_NAME = "orders";
        public static final String ID = BaseColumns._ID;
        public static final String PRODUCT_ID = "product_id";
        public static final String NUMBER = "number";

        public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PRODUCT_ID + " INTEGER," +
                NUMBER + " INTEGER);";

        public static final String DEFAULT_SORT_ORDER = ID + " ASC";
    }


}
