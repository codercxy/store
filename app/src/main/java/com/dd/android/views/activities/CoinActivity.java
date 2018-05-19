package com.dd.android.views.activities;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dd.android.R;
import com.dd.android.provider.DbProvider;
import com.dd.android.views.adapters.MyFragmentPagerAdapter;
import com.dd.android.views.fragments.CoinAllFragment;
import com.dd.android.views.fragments.CoinCashFragment;
import com.dd.android.views.fragments.CoinFriendFragment;
import com.dd.android.views.fragments.CoinShoppingFragment;

import java.util.ArrayList;
import java.util.List;

public class CoinActivity extends AppCompatActivity implements
        ViewPager.OnPageChangeListener, View.OnClickListener{

    private ViewPager pager;
    private List<Fragment> fragmentList;
    private TextView allText;
    private TextView shopText;
    private TextView friendText;
    private TextView cashText;

    private TextView coinText;
    private Button cashButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coin);

        initView();
        initFragments();
        initPager();
    }

    private void initView() {
        allText = (TextView) findViewById(R.id.coin_all);
        shopText = (TextView) findViewById(R.id.coin_shopping);
        friendText = (TextView) findViewById(R.id.coin_friend);
        cashText = (TextView) findViewById(R.id.coin_cash);

        coinText = (TextView) findViewById(R.id.coin);
        cashButton = (Button) findViewById(R.id.coin_btn_cash);

        allText.setOnClickListener(this);
        shopText.setOnClickListener(this);
        friendText.setOnClickListener(this);
        cashText.setOnClickListener(this);

        cashButton.setOnClickListener(this);
        initCoin();
    }

    private void initCoin() {
        DbProvider provider = new DbProvider();
        provider.init(this);
        coinText.setText(String.valueOf(provider.getWealth().getCoin()));
    }

    private void initFragments() {
        fragmentList = new ArrayList<Fragment>();
        fragmentList.add(new CoinAllFragment());
        fragmentList.add(new CoinShoppingFragment());
        fragmentList.add(new CoinFriendFragment());
        fragmentList.add(new CoinCashFragment());
    }

    private void initPager() {
        pager = (ViewPager) findViewById(R.id.coin_pager);
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragmentList);
        pager.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.coin_all:
                pager.setCurrentItem(0);
                break;
            case R.id.coin_shopping:
                pager.setCurrentItem(1);
                break;
            case R.id.coin_friend:
                pager.setCurrentItem(2);
                break;
            case R.id.coin_cash:
                pager.setCurrentItem(3);
                break;
            case R.id.coin_btn_cash:
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {

    }


}
