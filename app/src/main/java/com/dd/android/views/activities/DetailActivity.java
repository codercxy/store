package com.dd.android.views.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.android.R;
import com.dd.android.views.adapters.MyFragmentPagerAdapter;
import com.dd.android.views.fragments.DetailFirstFragment;
import com.dd.android.views.fragments.DetailSecondFragment;
import com.dd.android.views.fragments.DetailThirdFragment;
import com.dd.android.views.iviews.iDetailView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends FragmentActivity implements iDetailView,
        ViewPager.OnPageChangeListener, View.OnClickListener{

    private ViewPager pager;
    private List<Fragment> fragList;

    private TextView tab1;
    private TextView tab2;
    private TextView tab3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tab1 = (TextView) findViewById(R.id.detail_tab_p1);
        tab2 = (TextView) findViewById(R.id.detail_tab_p2);
        tab3 = (TextView) findViewById(R.id.detail_tab_p3);

        tab1.setOnClickListener(this);
        tab2.setOnClickListener(this);
        tab3.setOnClickListener(this);


        fragList = new ArrayList<Fragment>();
        fragList.add(new DetailFirstFragment());
        fragList.add(new DetailSecondFragment());
        fragList.add(new DetailThirdFragment());




        pager = (ViewPager) findViewById(R.id.detail_pager);

        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager(), fragList);

        pager.setAdapter(adapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.detail_tab_p1:
                pager.setCurrentItem(0);
                break;
            case R.id.detail_tab_p2:
                pager.setCurrentItem(1);
                break;
            case R.id.detail_tab_p3:
                pager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }



    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Toast.makeText(DetailActivity.this, "now in"+(position+1)+"page", Toast.LENGTH_SHORT).show();
    }
}
