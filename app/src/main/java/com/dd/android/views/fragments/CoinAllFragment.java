package com.dd.android.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dd.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CoinAllFragment extends BaseFragment {


    public CoinAllFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin_all, container, false);
    }

    @Override
    public void fetchData() {

    }
}
