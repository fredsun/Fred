package com.fred.utils;

import android.app.Service;
import android.os.Vibrator;
import android.support.multidex.MultiDexApplication;

import com.baidu.location.service.LocationService;
import com.baidu.mapapi.SDKInitializer;


/**
 * Created by Administrator on 2016/7/20.
 */
public class MyApplication extends MultiDexApplication {
    public LocationService locationService;
    public Vibrator mVibrator;
    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        SDKInitializer.initialize(getApplicationContext());

    }
}
