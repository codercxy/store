package com.dd.android.views.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dd.android.R;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by 57248 on 2016/8/17.
 */
public class FirstAdapter extends LoopPagerAdapter {
    private Integer[] imageArray;
    private int count;
    private View mView;

    private RollPagerView mRollViewPager;

    public FirstAdapter(View view, RollPagerView viewPager, Integer[] imageArray) {
        super(viewPager);
        this.imageArray = imageArray;
        this.count = imageArray.length;
        this.mView = view;
    }

    @Override
    public View getView(ViewGroup container, int position) {

        ImageView view = new ImageView(container.getContext());

        view.setImageResource(imageArray[position]);
        view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dialog = new Dialog(mView.getContext(), R.style.fullDialog);
                RelativeLayout contentView = (RelativeLayout) View.inflate(mView.getContext(), R.layout.detail_first_dialog, null);
                mRollViewPager = (RollPagerView) contentView.findViewById(R.id.detail_fr_dialog_view_pager);
                mRollViewPager.setAnimationDurtion(500);
                mRollViewPager.setAdapter(new FirstFullScreenAdapter(dialog, mRollViewPager, new Integer[] {R.drawable.blue_dialog, R.drawable.blue_dialog, R.drawable.blue_dialog}));
                dialog.setContentView(contentView);
                dialog.show();
            }
        });
        return view;
    }



    @Override
    public int getRealCount() {
        return count;
    }
}
