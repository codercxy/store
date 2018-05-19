package com.dd.android.views.adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import java.util.List;

/**
 * Created by 57248 on 2016/8/16.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragList;


    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragList) {
        super(fm);
        this.fragList = fragList;

    }

    @Override
    public Fragment getItem(int position) {
        return fragList.get(position);
    }



    @Override
    public int getCount() {
        return fragList.size();
    }
}
