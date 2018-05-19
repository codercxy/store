package com.dd.android.views.fragments;


import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dd.android.DDWineApp;
import com.dd.android.R;
import com.dd.android.model.CartItem;
import com.dd.android.model.Constants;
import com.dd.android.model.Order;
import com.dd.android.model.Product;
import com.dd.android.provider.DbOrders;
import com.dd.android.provider.DbProducts;
import com.dd.android.provider.DbProvider;
import com.dd.android.utils.RecyclerInsetsDecoration;
import com.dd.android.utils.RecyclerViewClickListener;
import com.dd.android.utils.TabLayout;
import com.dd.android.views.activities.MainActivity;
import com.dd.android.views.activities.OrderActivity;
import com.dd.android.views.adapters.CartAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CartFragment extends BaseFragment implements
        View.OnClickListener{

    private List<Order> mOrder;
    private List<Product> mProduct;
    private DDWineApp mApplication;
    private List<CartItem> cartList;
    private CartAdapter adapter;
    private RecyclerView mRecycler;

    private TextView title;
    private TextView submit;
    private TextView total;
    private TextView discount;
    private TextView benefit;

    private double totalNum = 0;

    public CartFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        initView(view);

        initData();

        initCartList();

        initRecycler();

        showItems(view);
        return view;
    }

    private void initData() {
        //mApplication = (DDWineApp) getActivity().getApplication();
        DbProvider provider = new DbProvider();
        provider.init(getActivity());
        mProduct = provider.getProductList();
        mOrder = provider.getOrderList();

        //mOrder = mApplication.getOrderList();
        //mProduct = mApplication.getProductList();

        updateTotalNum();

    }

    private void initView(View view) {
        mRecycler = (RecyclerView) view.findViewById(R.id.cart_item_recycler);
        submit = (TextView) view.findViewById(R.id.cart_submit);
        total = (TextView) view.findViewById(R.id.cart_total);
        discount = (TextView) view.findViewById(R.id.cart_discount);
        benefit = (TextView) view.findViewById(R.id.cart_benefit);

        submit.setOnClickListener(this);
    }

    private void initCartList() {
        cartList = new ArrayList<CartItem>();
        for (int i = 0; i < mOrder.size(); i++) {
            CartItem cartItem = new CartItem();
            int productId = mOrder.get(i).getProductId();
            cartItem.setPicId(mProduct.get(productId).getPicId_fr());
            cartItem.setTitle(mProduct.get(productId).getName());
            cartItem.setDisc(mProduct.get(productId).getDisc());
            cartItem.setNum(mOrder.get(i).getNumber());
            cartItem.setPrice(mProduct.get(productId).getPrice());
            cartItem.setCount(mOrder.get(i).getNumber() * mProduct.get(productId).getPrice());
            cartList.add(cartItem);
        }
    }

    private void initRecycler() {

        mRecycler.addItemDecoration(new RecyclerInsetsDecoration(getActivity()));
    }

    public void showItems(View view) {
        adapter = new CartAdapter(view, cartList, mApplication, mOrder);
        adapter.setRecyclerListener(this);
        mRecycler.setAdapter(adapter);
    }


    public void updateTotalNum() {
        if (mOrder.isEmpty()){
            total.setText("0");
            discount.setText("0");
            benefit.setText("0");
        } else {
            for (int i = 0; i < mOrder.size(); i++) {
                totalNum += mOrder.get(i).getNumber() * mProduct.get(mOrder.get(i).getProductId()).getPrice();
            }
            total.setText(String.valueOf(Constants.getCount(totalNum)));
            discount.setText(String.valueOf(Constants.getDiscount(totalNum)));
            benefit.setText(String.valueOf(Constants.getBenefit(totalNum)));
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cart_submit:
                Intent intent = new Intent();
                intent.setClass(getActivity(), OrderActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void fetchData() {

    }


}
