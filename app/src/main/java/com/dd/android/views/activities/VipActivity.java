package com.dd.android.views.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dd.android.R;

public class VipActivity extends Activity implements View.OnClickListener{

    private TextView detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip);

        detail = (TextView) findViewById(R.id.vip_detail);
        detail.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.vip_detail:
                Intent intent = new Intent();
                intent.setClass(VipActivity.this, VipDetActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
