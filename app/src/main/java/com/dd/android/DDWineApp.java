package com.dd.android;

import android.app.Application;

import com.dd.android.di.components.AppComponent;
import com.dd.android.model.Order;
import com.dd.android.model.Product;

import java.util.ArrayList;
import java.util.List;


public class DDWineApp extends Application{
    private AppComponent mAppComponent;



    @Override
    public void onCreate() {
        super.onCreate();
    }




    public AppComponent getmAppComponent() {
        return mAppComponent;
    }
}
