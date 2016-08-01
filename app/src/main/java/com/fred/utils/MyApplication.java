package com.fred.utils;

import android.support.multidex.MultiDexApplication;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Administrator on 2016/7/20.
 */
public class MyApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "900041542", false);
    }
}
