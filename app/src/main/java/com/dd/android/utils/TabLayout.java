package com.dd.android.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.dd.android.model.TabItem;

import java.util.ArrayList;

/**
 * Created by 57248 on 2016/8/18.
 */
public class TabLayout extends LinearLayout implements View.OnClickListener{

    private ArrayList<TabItem> tabs;
    private OnTabClickListener listener;

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public TabLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    private void initView() {
        setOrientation(HORIZONTAL);
    }

    public void initData(ArrayList<TabItem> tabs, OnTabClickListener listener) {
        this.tabs = tabs;
        this.listener = listener;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        if (tabs != null && tabs.size() > 0) {
            TabView mTabView = null;
            for (int i = 0; i < tabs.size(); i++) {
                mTabView = new TabView(getContext());
                mTabView.setTag(tabs.get(i));
                mTabView.initData(tabs.get(i));
                mTabView.setOnClickListener(this);
                addView(mTabView, params);
            }
        } else {
            throw new IllegalArgumentException("tabs can not be empty");
        }
    }

    @Override
    public void onClick(View view) {
        listener.onTabClick((TabItem)view.getTag());
    }

    public interface OnTabClickListener {

        void onTabClick(TabItem tabItem);
    }
}
