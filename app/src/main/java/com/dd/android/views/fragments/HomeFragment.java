package com.dd.android.views.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.android.DDWineApp;
import com.dd.android.R;
import com.dd.android.model.Product;
import com.dd.android.provider.DbProducts;
import com.dd.android.provider.DbProvider;
import com.dd.android.utils.RecyclerInsetsDecoration;
import com.dd.android.utils.RecyclerViewClickListener;
import com.dd.android.utils.TabLayout;
import com.dd.android.views.activities.AdsFirstActivity;
import com.dd.android.views.activities.AdsSecondActivity;
import com.dd.android.views.activities.AdsThridActivity;
import com.dd.android.views.activities.CodeActivity;
import com.dd.android.views.activities.DetailActivity;
import com.dd.android.views.activities.MainActivity;
import com.dd.android.views.adapters.MainAdapter;
import com.google.zxing.activity.CaptureActivity;
import com.google.zxing.encoding.EncodingHandler;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements RecyclerViewClickListener,
        OnBannerClickListener, View.OnClickListener{

    public static final int REQUEST_CODE = 0;
    private DDWineApp mApplication;
    private List<Product> productList;
    private Banner banner;
    public final static String EXTRA_POSITION = "position";
    private MainAdapter mMainAdapter;
    private String[] titles;
    private Integer[] picRes;

    private Intent intent;

    private TextView scanText;
    private TextView payText;
    private TextView codeText;
    //@InjectView(R.id.activity_main_recycler)
    RecyclerView mRecycler;
    /*@InjectView(R.id.activity_main_toolbar)
    Toolbar mToolbar;*/

    /*public static HomeFragment newInstance(String title) {
        // Required empty public constructor
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("title", title);
        homeFragment.setArguments(args);
        return homeFragment;
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initView(view);

        banner = (Banner) view.findViewById(R.id.home_banner);
        List<Integer> picList = new ArrayList<>();
        picList.add(R.drawable.wine);
        picList.add(R.drawable.wine);
        picList.add(R.drawable.wine);
        banner.setImages(picList);
        banner.setOnBannerClickListener(this);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        initData();


        mRecycler = (RecyclerView) view.findViewById(R.id.activity_main_recycler);

        initializeRecycler();

        showWines(titles, picRes);
        return view;
    }
    private void initView(View view) {
        scanText = (TextView) view.findViewById(R.id.home_scan);
        payText = (TextView) view.findViewById(R.id.home_pay);
        codeText = (TextView) view.findViewById(R.id.home_code);

        scanText.setOnClickListener(this);
        payText.setOnClickListener(this);
        codeText.setOnClickListener(this);
    }


    private void initData() {
        //mApplication = (DDWineApp) getActivity().getApplication();
        //productList = mApplication.getProductList();
        DbProvider provider = new DbProvider();
        provider.init(getActivity());
        productList = provider.getProductList();

        List<String> titleList = new ArrayList<String>();
        List<Integer> picList = new ArrayList<Integer>();
        for (int i = 0; i < productList.size(); i ++) {
            titleList.add(productList.get(i).getName());
            picList.add(productList.get(i).getPicId_fr());
        }

        titles = titleList.toArray(new String[1]);

        picRes = picList.toArray(new Integer[1]);
    }


    private void initializeRecycler() {


        mRecycler.addItemDecoration(new RecyclerInsetsDecoration(getActivity()));
    }


    public void showWines(String[] title, Integer[] pic) {
        mMainAdapter = new MainAdapter(title, pic);
        mMainAdapter.setRecyclerListListener(this);
        mRecycler.setAdapter(mMainAdapter);
    }

    @Override
    public void fetchData() {

    }

    @Override
    public void OnBannerClick(int position) {
        switch (position) {
            case 1:
                intent = new Intent();
                intent.setClass(getActivity(), AdsFirstActivity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent();
                intent.setClass(getActivity(), AdsSecondActivity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent();
                intent.setClass(getActivity(), AdsThridActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v,int position) {
        Intent homeFragmentIntent = new Intent(getActivity(), DetailActivity.class);

        homeFragmentIntent.putExtra(EXTRA_POSITION, position);

        startActivity(homeFragmentIntent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_scan:
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, REQUEST_CODE);
                break;
            case R.id.home_pay:
                break;
            case R.id.home_code:
                Intent code_intent = new Intent(getActivity(), CodeActivity.class);
                startActivity(code_intent);

                break;
            default:
                break;
        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == getActivity().RESULT_OK) { //RESULT_OK = -1
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            Toast.makeText(getActivity(), scanResult, Toast.LENGTH_LONG).show();
        }
    }
}
