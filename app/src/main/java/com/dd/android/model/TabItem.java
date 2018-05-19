package com.dd.android.model;

import com.dd.android.views.fragments.BaseFragment;

/**
 * Created by 57248 on 2016/8/18.
 */
public class TabItem {

    public int imageResId;

    public int lableResId;

    public Class<? extends BaseFragment> tagFragmentClz;

    public TabItem(int imageResId, int lableResId, Class<? extends BaseFragment> tagFragmentClz) {
        this.imageResId = imageResId;
        this.lableResId = lableResId;
        this.tagFragmentClz = tagFragmentClz;
    }
}
