package com.dd.android.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.android.R;
import com.dd.android.model.TabItem;

/**
 * Created by 57248 on 2016/8/18.
 */
public class TabView extends LinearLayout implements View.OnClickListener {

    private ImageView tabImage;
    private TextView tabText;

    public TabView(Context context) {
        super(context);
        initView(context);
    }

    public TabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context);
    }

    private void initView(Context context) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        LayoutInflater.from(context).inflate(R.layout.tab_view, this, true);
        tabImage = (ImageView) findViewById(R.id.tab_image);
        tabText = (TextView) findViewById(R.id.tab_lable);
    }

    public void initData(TabItem tabItem) {
         tabImage.setImageResource(tabItem.imageResId);
        tabText.setText(tabItem.lableResId);
    }

    @Override
    public void onClick(View view) {

    }


}
