package com.dd.android.views.fragments;


import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.android.DDWineApp;
import com.dd.android.R;
import com.dd.android.model.Constants;
import com.dd.android.model.Order;
import com.dd.android.model.Product;
import com.dd.android.provider.DbOrders;
import com.dd.android.provider.DbProducts;
import com.dd.android.provider.DbProvider;
import com.dd.android.views.activities.MainActivity;
import com.dd.android.views.adapters.FirstAdapter;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.TextHintView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFirstFragment extends Fragment implements View.OnClickListener{

    private RollPagerView mRollViewPager;
    private DDWineApp mApplication;
    private List<Product> productList;
    private int productId;

    private List<Order> mOrder;
    private int myNum;

    /*TextViews*/
    private TextView titleText;
    private TextView discText;
    private TextView priceText;
    private TextView numText;
    private ImageView addImg;
    private ImageView minusImg;
    private TextView pd_numText, pd_amountText, pd_titleText, pd_noteText, pd_typeText;
    private TextView count;
    private TextView discount;
    private TextView benefits;
    /*
    *图片资源ID
    * */
    private Integer[] imgIdArray;
    private TextView cartView;
    private TextView addCartView;
    private TextView buyView;
    //private TextView intentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        initData();
        initView(view);

        mRollViewPager = (RollPagerView) view.findViewById(R.id.roll_view_pager);
        mRollViewPager.setAnimationDurtion(500);
        mRollViewPager.setAdapter(new FirstAdapter(view, mRollViewPager, imgIdArray));

        TextHintView hintView = new TextHintView(getActivity());
        hintView.setTextColor(Color.BLACK);
        Log.e("hint_color", String.valueOf(hintView.getCurrentHintTextColor()));
        mRollViewPager.setHintView(hintView);




        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.detail_bottom_cart:

                Intent intent = new Intent();
                intent.setClass(getActivity(), MainActivity.class);
                intent.putExtra("fragmentTAG", 1);
                startActivity(intent);

                break;
            case R.id.detail_first_img_add:
                myNum = Integer.parseInt((String) numText.getText());
                numText.setText(String.valueOf(myNum + 1));

                double add_price = productList.get(productId).getPrice() * (myNum + 1);

                count.setText(String.valueOf(Constants.getCount(add_price)));
                discount.setText(String.valueOf(Constants.getDiscount(add_price)));
                benefits.setText(String.valueOf(Constants.getBenefit(add_price)));
                break;
            case R.id.detail_first_img_minus:
                myNum = Integer.parseInt((String) numText.getText());
                if (myNum != 1) {
                    numText.setText(String.valueOf(myNum - 1));
                    double minus_price = productList.get(productId).getPrice() * (myNum - 1);

                    count.setText(String.valueOf(Constants.getCount(minus_price)));
                    discount.setText(String.valueOf(Constants.getDiscount(minus_price)));
                    benefits.setText(String.valueOf(Constants.getBenefit(minus_price)));
                }
                break;
            case R.id.detail_bottom_addcart:
                myNum = Integer.parseInt((String) numText.getText());
                updateOrder(productId, myNum);

                Toast.makeText(getActivity(), "已加入购物车", Toast.LENGTH_SHORT).show();
                break;
            case R.id.detail_bottom_buy:
                break;
            default:
                break;
        }
    }





    private void initData() {
        productId = getActivity().getIntent().getIntExtra(HomeFragment.EXTRA_POSITION, 0);

        DbProvider provider = new DbProvider();
        provider.init(getActivity());
        productList = provider.getProductList();
        mOrder = provider.getOrderList();



        imgIdArray = new Integer[]{
                productList.get(productId).getPicId_fr(),
                productList.get(productId).getPicId_se(),
                productList.get(productId).getPicId_th()};

    }

    private void initView(View view) {
        titleText = (TextView) view.findViewById(R.id.detail_first_title);
        discText = (TextView) view.findViewById(R.id.detail_first_subtitle);
        priceText = (TextView) view.findViewById(R.id.detail_first_price);
        numText = (TextView) view.findViewById(R.id.detail_first_num);
        addImg = (ImageView) view.findViewById(R.id.detail_first_img_add);
        minusImg = (ImageView) view.findViewById(R.id.detail_first_img_minus);

        pd_amountText = (TextView) view.findViewById(R.id.detail_first_pd_amount);
        pd_noteText = (TextView) view.findViewById(R.id.detail_first_pd_note);
        pd_numText = (TextView) view.findViewById(R.id.detail_first_pd_num);
        pd_titleText = (TextView) view.findViewById(R.id.detail_first_pd_title);
        pd_typeText = (TextView) view.findViewById(R.id.detail_first_pd_type);

        cartView = (TextView) view.findViewById(R.id.detail_bottom_cart);
        addCartView = (TextView) view.findViewById(R.id.detail_bottom_addcart);
        buyView = (TextView) view.findViewById(R.id.detail_bottom_buy);

        count = (TextView) view.findViewById(R.id.detail_first_count);
        discount = (TextView) view.findViewById(R.id.detail_first_discount);
        benefits = (TextView) view.findViewById(R.id.detail_first_benefits);

        cartView.setOnClickListener(this);
        addCartView.setOnClickListener(this);
        buyView.setOnClickListener(this);

        addImg.setOnClickListener(this);
        minusImg.setOnClickListener(this);

        titleText.setText(productList.get(productId).getName());
        discText.setText(productList.get(productId).getDisc());
        priceText.setText(String.valueOf(productList.get(productId).getPrice()));
        numText.setText("1");

        pd_amountText.setText(productList.get(productId).getAmount());
        pd_noteText.setText(productList.get(productId).getNote());
        pd_numText.setText(productList.get(productId).getNumber());
        pd_titleText.setText(productList.get(productId).getName());
        pd_typeText.setText(productList.get(productId).getType());

        count.setText(String.valueOf(Constants.getCount(productList.get(productId).getPrice())));
        discount.setText(String.valueOf(Constants.getDiscount(productList.get(productId).getPrice())));
        benefits.setText(String.valueOf(Constants.getBenefit(productList.get(productId).getPrice())));
    }

    private void updateOrder(int productId, int num) {
        DbProvider provider = new DbProvider();
        provider.init(getContext());
        //mOrder = mApplication.getOrderList();
        Boolean isNull = true;
        if (!mOrder.isEmpty()) {
            for (int i = 0; i < mOrder.size(); i++) {
                if (productId == mOrder.get(i).getProductId()) {
                    mOrder.get(i).setNumber(num + mOrder.get(i).getNumber());

                    ContentValues values = new ContentValues();
                    values.put(DbOrders.Order.PRODUCT_ID, productId);
                    values.put(DbOrders.Order.NUMBER, mOrder.get(i).getNumber());
                    String[] args = {String.valueOf(productId)};
                    provider.update(DbOrders.CONTENT_URI, values, "product_id = ?", args);

                    isNull = false;
                }
            }
        }

        if (isNull) {
            Order newOrder = new Order();
            newOrder.setProductId(productId);
            newOrder.setNumber(num);
            mOrder.add(newOrder);

            ContentValues newValue = new ContentValues();
            newValue.put(DbOrders.Order.PRODUCT_ID, productId);
            newValue.put(DbOrders.Order.NUMBER, num);
            provider.insert(DbOrders.CONTENT_URI, newValue);
        }

    }



}
