package com.dd.android.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.dd.android.R;
import com.dd.android.views.activities.VipActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends BaseFragment implements View.OnClickListener{


    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_me, container, false);

        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.me_layout_vip:
                Intent intent = new Intent();
                intent.setClass(getActivity(), VipActivity.class);
                startActivity(intent);
                break;*/
            default:
                break;
        }
    }

    @Override
    public void fetchData() {

    }
}
