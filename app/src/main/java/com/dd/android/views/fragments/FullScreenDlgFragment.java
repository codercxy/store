package com.dd.android.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dd.android.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FullScreenDlgFragment extends BaseFragment {


    public FullScreenDlgFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_first_dialog, container, false);
        return view;
    }

    @Override
    public void fetchData() {

    }
}
