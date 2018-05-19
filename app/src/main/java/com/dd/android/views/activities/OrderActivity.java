package com.dd.android.views.activities;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.android.DDWineApp;
import com.dd.android.R;
import com.dd.android.model.CartItem;
import com.dd.android.model.Constants;
import com.dd.android.model.Order;
import com.dd.android.model.Product;
import com.dd.android.provider.DbOrders;
import com.dd.android.provider.DbProducts;
import com.dd.android.provider.DbProvider;
import com.dd.android.utils.RecyclerViewClickListener;
import com.dd.android.utils.RecyclerViewDivider;
import com.dd.android.views.adapters.OrderAdaper;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity implements RecyclerViewClickListener
        , View.OnClickListener{

    private List<Order> mOrder;
    private List<Product> mProduct;
    private DDWineApp mApplication;
    private List<CartItem> orderList;

    private OrderAdaper adaper;
    private Button newAddr;
    private RecyclerView mRecycler;

    private TextView total;
    private TextView benefit;
    private TextView submit;
    private double totalNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        initView();

        initData();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_order);
        newAddr = (Button) findViewById(R.id.btn_order_new_addr);

        newAddr.setOnClickListener(this);
        ((AppCompatActivity) this).setSupportActionBar(toolbar);


        initOrderList();

        initRecyclerView();

        showItems();
    }
    private void initData(){
       // mApplication = (DDWineApp) getApplication();
        //mOrder = mApplication.getOrderList();
        //mProduct = mApplication.getProductList();
        DbProvider provider = new DbProvider();
        provider.init(this);
        mOrder = provider.getOrderList();
        mProduct = provider.getProductList();

        updateTotalNum();
    }

    private void initView() {
        total = (TextView) findViewById(R.id.order_total);
        benefit = (TextView) findViewById(R.id.order_benefit);
        submit = (TextView) findViewById(R.id.order_submit);

        submit.setOnClickListener(this);
    }
    private void initOrderList() {
        orderList = new ArrayList<>();
        for (int i = 0; i < mOrder.size(); i++) {
            CartItem item = new CartItem();
            int productId = mOrder.get(i).getProductId();
            item.setTitle(mProduct.get(productId).getName());
            item.setDisc(mProduct.get(productId).getDisc());
            item.setPrice(mProduct.get(productId).getPrice());
            item.setPicId(mProduct.get(productId).getPicId_fr());
            item.setNum(mOrder.get(i).getNumber());
            item.setCount(mOrder.get(i).getNumber() * mProduct.get(productId).getPrice());
            orderList.add(item);
        }

    }

    private void initRecyclerView() {
        mRecycler = (RecyclerView) findViewById(R.id.order_recycler);
        mRecycler.addItemDecoration(new RecyclerViewDivider(this, LinearLayoutManager.VERTICAL));
    }

    private void showItems() {
        adaper = new OrderAdaper(orderList);
        adaper.setRecyclerListener(this);
        mRecycler.setAdapter(adaper);

    }

    private void updateTotalNum() {
        if (mOrder.isEmpty()){
            total.setText("0");
            benefit.setText("0");
        } else {
            for (int i = 0; i < mOrder.size(); i++) {
                totalNum += mOrder.get(i).getNumber() * mProduct.get(mOrder.get(i).getProductId()).getPrice();
            }
            total.setText(String.valueOf(Constants.getCount(totalNum)));
            benefit.setText(String.valueOf(Constants.getBenefit(totalNum)));
        }
    }

    @Override
    public void onClick(View v, int position) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_order_new_addr:
                Intent intent = new Intent();
                intent.setClass(OrderActivity.this, NewAddrActivity.class);
                startActivity(intent);
                break;
            case R.id.order_submit:
                Toast.makeText(OrderActivity.this, "订单已提交", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
