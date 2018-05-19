package com.dd.android.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dd.android.model.Order;
import com.dd.android.model.Product;
import com.dd.android.model.Wealth;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 57248 on 2016/9/2.
 */
public class DbProvider extends ContentProvider{


    private static final int PRODUCTS = 0;
    private static final int USER = 1;
    private static final int ORDERS = 2;
    private static final int WEALTH = 3;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(DbProducts.PROVIDER_NAME, DbProducts.Products.TABLE_NAME, PRODUCTS);
        uriMatcher.addURI(DbOrders.PROVIDER_NAME, DbOrders.Order.TABLE_NAME, ORDERS);
        uriMatcher.addURI(DbWealth.PROVIDER_NAME, DbWealth.Wealth.TABLE_NAME, WEALTH);
    }

    private SQLiteDatabase database;
    private Context context;


    @Override
    public boolean onCreate() {
        Log.e("db_create", "success!");
        DbHelper dbHelper = new DbHelper(getContext());
        database = dbHelper.getWritableDatabase();
        Log.e("db_created", "success!");
        return (database != null);
    }
    public void init(Context context) {
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long rowId;

        switch (uriMatcher.match(uri)) {
            case PRODUCTS:

                rowId = database.insert(DbProducts.Products.TABLE_NAME, null, values);
                Log.e("db_product", "success!");
                if (rowId > 0) {
                    uri = ContentUris.withAppendedId(DbProducts.CONTENT_URI, rowId);
                    context.getContentResolver().notifyChange(uri, null);
                }
                break;
            case ORDERS:
                rowId = database.insert(DbOrders.Order.TABLE_NAME, null, values);

                if (rowId > 0) {
                    uri = ContentUris.withAppendedId(DbOrders.CONTENT_URI, rowId);
                    context.getContentResolver().notifyChange(uri, null);
                }
                break;
            case WEALTH:
                rowId = database.insert(DbWealth.Wealth.TABLE_NAME, null, values);
                if (rowId > 0) {
                    uri = ContentUris.withAppendedId(DbWealth.CONTENT_URI, rowId);
                    context.getContentResolver().notifyChange(uri, null);
                }
                break;

            default:
                Log.e("db_product", "exception!");
                throw new SQLException("Failed to insert row into" + uri);
        }
        return uri;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        switch (uriMatcher.match(uri)) {
            case PRODUCTS:
                queryBuilder.setTables(DbProducts.Products.TABLE_NAME);
                if (sortOrder == null) {
                    sortOrder = DbProducts.Products.DEFAULT_SORT_ORDER;
                }
                break;
            case ORDERS:
                queryBuilder.setTables(DbOrders.Order.TABLE_NAME);
                if (sortOrder == null) {
                    sortOrder = DbOrders.Order.DEFAULT_SORT_ORDER;
                }
                break;
            case WEALTH:
                queryBuilder.setTables(DbWealth.Wealth.TABLE_NAME);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        Cursor c = queryBuilder.query(database, projection, selection, selectionArgs,
                null, null, sortOrder);
        c.setNotificationUri(context.getContentResolver(), uri);
        return c;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted = 0;

        switch (uriMatcher.match(uri)) {
            case PRODUCTS:
                rowsDeleted = database.delete(DbProducts.Products.TABLE_NAME, selection, selectionArgs);
                break;
            case ORDERS:
                rowsDeleted = database.delete(DbOrders.Order.TABLE_NAME, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri" + uri);
        }
        context.getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        int rowsUpdated = 0;

        switch (uriMatcher.match(uri)) {
            case PRODUCTS:
                rowsUpdated = database.update(DbProducts.Products.TABLE_NAME, values, selection, selectionArgs);
                break;
            case ORDERS:
                rowsUpdated = database.update(DbOrders.Order.TABLE_NAME, values, selection, selectionArgs);
                break;
            case WEALTH:
                rowsUpdated = database.update(DbWealth.Wealth.TABLE_NAME, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri" + uri);
        }
        context.getContentResolver().notifyChange(uri, null);
        return rowsUpdated;
    }

    public List<Product> getProductList() {
        List<Product> productList = new ArrayList<>();

        String[] projection = new String[]{DbProducts.Products.PRODUCT_ID, DbProducts.Products.NAME,
                DbProducts.Products.DISC, DbProducts.Products.PICID_FIRST, DbProducts.Products.PICID_SECOND,
                DbProducts.Products.PICID_THIRD, DbProducts.Products.PRICE, DbProducts.Products.NUMBER,
                DbProducts.Products.AMOUNT, DbProducts.Products.NOTE, DbProducts.Products.TYPE};
        Cursor c = query(DbProducts.CONTENT_URI, projection, null, null, null);
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    Product product = new Product();
                    product.setProduct_id(c.getInt(c.getColumnIndexOrThrow(DbProducts.Products.PRODUCT_ID)));
                    product.setName(c.getString(c.getColumnIndexOrThrow(DbProducts.Products.NAME)));
                    product.setDisc(c.getString(c.getColumnIndexOrThrow(DbProducts.Products.DISC)));
                    product.setPicId_fr(c.getInt(c.getColumnIndexOrThrow(DbProducts.Products.PICID_FIRST)));
                    product.setPicId_se(c.getInt(c.getColumnIndexOrThrow(DbProducts.Products.PICID_SECOND)));
                    product.setPicId_th(c.getInt(c.getColumnIndexOrThrow(DbProducts.Products.PICID_THIRD)));
                    product.setPrice(c.getDouble(c.getColumnIndexOrThrow(DbProducts.Products.PRICE)));
                    product.setNumber(c.getString(c.getColumnIndexOrThrow(DbProducts.Products.NUMBER)));
                    product.setAmount(c.getString(c.getColumnIndexOrThrow(DbProducts.Products.AMOUNT)));
                    product.setNote(c.getString(c.getColumnIndexOrThrow(DbProducts.Products.NOTE)));
                    product.setType(c.getString(c.getColumnIndexOrThrow(DbProducts.Products.TYPE)));
                    productList.add(product);

                } while (c.moveToNext());
            }
            c.close();
        }
        return productList;
    }

    public List<Order> getOrderList() {
        List<Order> orderList = new ArrayList<Order>();
        String[] projection = new String[] {DbOrders.Order.PRODUCT_ID, DbOrders.Order.NUMBER};
        Cursor c  = query(DbOrders.CONTENT_URI, projection, null, null, null);
        if (c.getCount() > 0) {
            if (c !=null) {
                if (c.moveToFirst()){
                    do {
                        Order order = new Order();
                        order.setProductId(c.getInt(c.getColumnIndexOrThrow(DbOrders.Order.PRODUCT_ID)));
                        order.setNumber(c.getInt(c.getColumnIndexOrThrow(DbOrders.Order.NUMBER)));

                        orderList.add(order);
                    } while (c.moveToNext());
                }
                c.close();
            }
        }
        return orderList;
    }

    public Wealth getWealth() {
        Wealth mWealth = new Wealth();
        String[] projection = new String[]{DbWealth.Wealth.COIN, DbWealth.Wealth.BALANCE,
                DbWealth.Wealth.BENEFIT, DbWealth.Wealth.DISCOUNT_TYPE};
        Cursor c = query(DbWealth.CONTENT_URI, projection, null, null, null);
        if (c.getCount() > 0) {
            if (c != null) {
                if (c.moveToFirst()) {
                    mWealth.setCoin(c.getInt(c.getColumnIndexOrThrow(DbWealth.Wealth.COIN)));
                    mWealth.setBalance(c.getDouble(c.getColumnIndexOrThrow(DbWealth.Wealth.BALANCE)));
                    mWealth.setBenefit(c.getInt(c.getColumnIndexOrThrow(DbWealth.Wealth.BENEFIT)));
                    mWealth.setDiscount_type(c.getInt(c.getColumnIndexOrThrow(DbWealth.Wealth.DISCOUNT_TYPE)));
                }
            }
        } else {
            ContentValues values = new ContentValues();
            values.put(DbWealth.Wealth.COIN, 50);
            values.put(DbWealth.Wealth.BALANCE, 0);
            values.put(DbWealth.Wealth.BENEFIT, 0);
            values.put(DbWealth.Wealth.DISCOUNT_TYPE, 0);
            insert(DbWealth.CONTENT_URI, values);

            mWealth.setCoin(50);
            mWealth.setBalance(0);
            mWealth.setBenefit(0);
            mWealth.setDiscount_type(0);
        }
        return mWealth;
    }

}
