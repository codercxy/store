package com.dd.android.presenters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.dd.android.DDWineApp;
import com.dd.android.R;
import com.dd.android.model.Product;
import com.dd.android.provider.DbProducts;
import com.dd.android.provider.DbProvider;
import com.dd.android.views.iviews.iMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by 57248 on 2016/8/14.
 */
public class MainPresenter {

    private iMainView mMainView;
    private Context context;

    private DDWineApp mApplication;
    private List<Product> productList;

    @Inject
    public MainPresenter(Context context) {
        this.context = context;

    }

    public void attachView (iMainView mainView) {

        mMainView = mainView;
    }
    public List<Product> getProductList() {

        String[] titles = new String[]{"金陵春·蓝金陵", "金陵春·绿金陵", "鸿运·鸿运当头", "南京大学周年纪念酒"};
        String[] disc = new String[]{"42°金陵春酒",
                "42°金陵春酒",
                "42°金陵春酒",
                "42°金陵春酒"};
        Integer[] picId_fr = new Integer[]{R.drawable.blue, R.drawable.green, R.drawable.red, R.drawable.nju};
        Integer[] picId_se = new Integer[]{R.drawable.blue, R.drawable.green, R.drawable.red, R.drawable.nju};
        Integer[] picId_th = new Integer[]{R.drawable.blue, R.drawable.green, R.drawable.red, R.drawable.nju};
        double[] price = new double[]{88.0, 198.0, 298.0, 398.0};
        String[] number = new String[]{"1181054016", "1181011916", "118940316", "1181159716"};
        String[] amount = new String[]{"500mL", "500mL", "500mL", "500mL"};
        String[] note = new String[]{"浓香型白酒", "浓香型白酒", "浓香型白酒", "浓香型白酒"};
        String[] type = new String[]{"金陵春·经典", "金陵春·经典", "金陵春·鸿运", "金陵春·南京大学纪念酒"};

        productList = new ArrayList<>();
        for (int i = 0; i < titles.length ; i++) {
            Product product = new Product();
            product.setProduct_id(i);
            product.setName(titles[i]);
            product.setDisc(disc[i]);
            product.setPicId_fr(picId_fr[i]);
            product.setPicId_se(picId_se[i]);
            product.setPicId_th(picId_th[i]);
            product.setPrice(price[i]);
            product.setNumber(number[i]);
            product.setAmount(amount[i]);
            product.setNote(note[i]);
            product.setType(type[i]);
            productList.add(product);
        }

        DbProvider provider = new DbProvider();
        provider.init(context);
        Cursor c = provider.query(DbProducts.CONTENT_URI,
                new String[] {DbProducts.Products.ID},
                null, null, null);

        //Log.e("my_cursor_id", c.getString(c.getColumnIndexOrThrow(DbProducts.Products.ID)));
        //Log.e("my_cursor_name", c.getString(c.getColumnIndexOrThrow(DbProducts.Products.NAME)));
        //Log.e("cursor_home", String.valueOf(c.getCount()));
        if (c.getCount() < 1) {

            for (int i = 0; i < productList.size(); i++) {
                ContentValues contentValues = new ContentValues();
                //contentValues.put(DbProducts.Products.ID, productList.get(i).getProduct_id());
                contentValues.put(DbProducts.Products.PRODUCT_ID, productList.get(i).getProduct_id());
                contentValues.put(DbProducts.Products.NAME, productList.get(i).getName());
                contentValues.put(DbProducts.Products.DISC, productList.get(i).getDisc());
                contentValues.put(DbProducts.Products.PICID_FIRST, productList.get(i).getPicId_fr());
                contentValues.put(DbProducts.Products.PICID_SECOND, productList.get(i).getPicId_se());
                contentValues.put(DbProducts.Products.PICID_THIRD, productList.get(i).getPicId_th());
                contentValues.put(DbProducts.Products.PRICE, productList.get(i).getPrice());
                contentValues.put(DbProducts.Products.NUMBER, productList.get(i).getNumber());
                contentValues.put(DbProducts.Products.AMOUNT, productList.get(i).getAmount());
                contentValues.put(DbProducts.Products.NOTE, productList.get(i).getNote());
                contentValues.put(DbProducts.Products.TYPE, productList.get(i).getType());
                Log.e("contentvalues_name", String.valueOf(contentValues.get("pic_id_fr")));
                provider.insert(DbProducts.CONTENT_URI, contentValues);

            }
        }


        //mApplication.updateOrderList(orderList);        //!!!需要存储至本地
        return productList;
    }


}
