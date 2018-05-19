package com.dd.android.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 57248 on 2016/9/2.
 */
public class DbHelper extends SQLiteOpenHelper{

    public DbHelper(Context context) {
        super(context, DbProducts.DB_NAME, null, DbProducts.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db.isReadOnly()) {
            db = getWritableDatabase();
        }
        db.execSQL(DbProducts.Products.CREATE_SQL);
        db.execSQL(DbOrders.Order.CREATE_SQL);
        db.execSQL(DbWealth.Wealth.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
