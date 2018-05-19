package com.dd.android.views.adapters;

import android.app.Activity;
import android.app.Dialog;
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
 * Created by 57248 on 2016/9/6.
 */
public class FirstFullScreenAdapter extends LoopPagerAdapter {
    private Integer[] imageArray;
    private int count;
    private Dialog dialog;

    public FirstFullScreenAdapter(Dialog dialog, RollPagerView viewPager, Integer[] imageArray) {
        super(viewPager);
        this.imageArray = imageArray;
        this.count = imageArray.length;
        this.dialog = dialog;
    }

    @Override
    public View getView(ViewGroup container, int position) {

        ImageView view = new ImageView(container.getContext());
        PhotoViewAttacher mAttacher=new PhotoViewAttacher(view);
        mAttacher.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setImageResource(imageArray[position]);
        //view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("first_dialog_full", "success!");
                dialog.dismiss();
            }
        });

        return view;
    }



    @Override
    public int getRealCount() {
        return count;
    }
}
