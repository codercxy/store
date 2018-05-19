package com.dd.android.views.activities;

import android.content.Context;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;

import com.dd.android.DDWineApp;
import com.dd.android.R;
import com.dd.android.model.Order;
import com.dd.android.model.Product;
import com.dd.android.model.TabItem;
import com.dd.android.presenters.MainPresenter;
import com.dd.android.utils.TabLayout;
import com.dd.android.views.fragments.BaseFragment;
import com.dd.android.views.fragments.CartFragment;
import com.dd.android.views.fragments.HomeFragment;
import com.dd.android.views.fragments.MeFragment;
import com.dd.android.views.fragments.WealthFragment;
import com.dd.android.views.iviews.iMainView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements iMainView,
        TabLayout.OnTabClickListener{

    private DDWineApp mApplication;
    private List<Product> productList;
    private List<Order> orderList;
    public static TabLayout mTabLayout;
    @Inject
    MainPresenter mMainPresenter;

    private static int fragmentTAG;

    private long mExitTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ButterKnife.inject(this);

        mMainPresenter = new MainPresenter(this);
        initProductList();
        initView();
        initData();
        initFragment();
    }
    private void initProductList() {
        productList = mMainPresenter.getProductList();

        //mApplication = (DDWineApp) getApplication();
        //mApplication.setUpProductList(productList);
    }


    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.tab_layout);
    }

    private void initData() {
        ArrayList<TabItem> tabs = new ArrayList<>();
        tabs.add(new TabItem(R.drawable.ic_home_grey600_24dp, R.string.home, HomeFragment.class));
        tabs.add(new TabItem(R.drawable.ic_shopping_cart_grey600_24dp, R.string.cart, CartFragment.class));
        tabs.add(new TabItem(R.drawable.ic_credit_card_grey600_24dp, R.string.wealth, WealthFragment.class));
        tabs.add(new TabItem(R.drawable.ic_person_grey600_24dp, R.string.me, MeFragment.class));

        mTabLayout.initData(tabs, this);


        fragmentTAG = getIntent().getIntExtra("fragmentTAG", 0);


    }



    private void initFragment() {
        switch (fragmentTAG) {
            case 0:
                HomeFragment homeFragment = new HomeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, homeFragment)
                        .commitAllowingStateLoss();
                break;
            case 1:
                CartFragment cartFragment = new CartFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, cartFragment)
                        .commitAllowingStateLoss();
                break;
            case 2:
                MeFragment meFragment = new MeFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment, meFragment)
                        .commitAllowingStateLoss();
                break;
            default:
                break;
        }

    }
    @Override
    public void onTabClick(TabItem tabItem) {
        try {
            BaseFragment fragment = tabItem.tagFragmentClz.newInstance();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment, fragment)
                    .commitAllowingStateLoss();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK && fragmentTAG == 0) {

            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //如果两次按键时间间隔大于2000ms,则不退出
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                System.exit(0); //否则退出程序
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
