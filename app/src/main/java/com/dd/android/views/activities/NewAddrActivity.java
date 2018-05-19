package com.dd.android.views.activities;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.dd.android.R;
import com.dd.android.views.dialog.DialogAddrFragment;

public class NewAddrActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView location;
    private TextView district;

    private NewAddrActivity mInstance;

    public NewAddrActivity getInstance() {
        if (mInstance == null) {
            mInstance = NewAddrActivity.this;
        }
        return mInstance;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_addr);

        initView();
    }
    private void initView() {
        location = (TextView) findViewById(R.id.new_addr_loc);
        district = (TextView) findViewById(R.id.new_addr_district);

        location.setOnClickListener(this);
        district.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.new_addr_loc:
                Log.e("new_addr_loc", "here");
                /*DialogFragment dialogAddr = new DialogAddrFragment();
                dialogAddr.show(getSupportFragmentManager(), "dialog_addr");*/
                new DialogAddrFragment().show(getSupportFragmentManager(), DialogAddrFragment.class.getSimpleName());
                break;
            case R.id.new_addr_district:
                break;
            default:
                break;
        }
    }
}
