package com.dd.android.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.android.R;
import com.dd.android.model.Wealth;
import com.dd.android.provider.DbProvider;
import com.dd.android.provider.DbWealth;
import com.dd.android.views.activities.BalanceActivity;
import com.dd.android.views.activities.BenefitActivity;
import com.dd.android.views.activities.CoinActivity;
import com.dd.android.views.activities.DiscountActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class WealthFragment extends BaseFragment implements View.OnClickListener{

    private LinearLayout coin_ll;
    private LinearLayout balance_ll;
    private LinearLayout benefit_ll;
    private LinearLayout discount_ll;

    private TextView coinText;
    private TextView balanceText;
    private TextView benefitText;
    private TextView typeText;

    private Intent intent;

    public WealthFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wealth, container, false);
        initView(view);

        return view;
    }
    private void initView(View view) {
        coin_ll = (LinearLayout) view.findViewById(R.id.wealth_layout_coin);
        balance_ll = (LinearLayout) view.findViewById(R.id.wealth_layout_balance);
        benefit_ll = (LinearLayout) view.findViewById(R.id.wealth_layout_benefit);
        discount_ll = (LinearLayout) view.findViewById(R.id.wealth_layout_discount);

        coinText = (TextView) view.findViewById(R.id.wealth_coin_num);
        balanceText = (TextView) view.findViewById(R.id.wealth_balance_num);
        benefitText = (TextView) view.findViewById(R.id.wealth_benefit_num);
        typeText = (TextView) view.findViewById(R.id.wealth_discount_num);

        coin_ll.setOnClickListener(this);
        balance_ll.setOnClickListener(this);
        benefit_ll.setOnClickListener(this);
        discount_ll.setOnClickListener(this);

        initTextView();
    }

    private void initTextView() {
        DbProvider provider = new DbProvider();
        provider.init(getActivity());
        Wealth mWealth = provider.getWealth();
        coinText.setText(String.valueOf(mWealth.getCoin()));
        balanceText.setText(String.valueOf(mWealth.getBalance()));
        benefitText.setText(String.valueOf(mWealth.getBenefit()));
        typeText.setText(String.valueOf(mWealth.getDiscount_type()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wealth_layout_coin:
                intent = new Intent();
                intent.setClass(getActivity(), CoinActivity.class);
                startActivity(intent);
                break;
            case R.id.wealth_layout_balance:
                intent = new Intent();
                intent.setClass(getActivity(), BalanceActivity.class);
                startActivity(intent);
                break;
            case R.id.wealth_layout_benefit:
                intent = new Intent();
                intent.setClass(getActivity(), BenefitActivity.class);
                startActivity(intent);
                break;
            case R.id.wealth_layout_discount:
                intent = new Intent();
                intent.setClass(getActivity(), DiscountActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void fetchData() {

    }
}
