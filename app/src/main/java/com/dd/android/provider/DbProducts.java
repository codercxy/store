package com.dd.android.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by 57248 on 2016/9/2.
 */
public final class DbProducts {

    public static final String PROVIDER_NAME = "com.dd.android.provider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME + "/"
            + Products.TABLE_NAME);

    public final static String DB_NAME = "ddwine.db";
    public final static int DB_VERSION = 1;

    public class Products{
        public static final String TABLE_NAME = "products";
        public static final String ID = BaseColumns._ID;
        public static final String PRODUCT_ID = "product_id";
        public static final String NAME = "name";
        public static final String DISC = "disc";
        public static final String PICID_FIRST = "pic_id_fr";
        public static final String PICID_SECOND = "pic_id_se";
        public static final String PICID_THIRD = "pic_id_th";
        public static final String PRICE = "price";
        public static final String NUMBER = "number";
        public static final String AMOUNT = "amount";
        public static final String NOTE = "note";
        public static final String TYPE = "type";

        public static final String CREATE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                PRODUCT_ID + " INTEGER," +
                NAME + " TEXT," +
                DISC + " TEXT," +
                PICID_FIRST + " INTEGER," +
                PICID_SECOND + " INTEGER," +
                PICID_THIRD + " INTEGER," +
                PRICE + " REAL," +
                NUMBER + " TEXT," +
                AMOUNT + " TEXT," +
                NOTE + " TEXT," +
                TYPE + " TEXT);";

        public static final String DEFAULT_SORT_ORDER = ID + " ASC";
    }
}
