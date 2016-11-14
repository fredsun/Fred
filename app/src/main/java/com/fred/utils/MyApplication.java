package com.fred.utils;

import android.os.Vibrator;
import android.support.multidex.MultiDexApplication;
import android.widget.Toast;


/**
 * Created by Administrator on 2016/7/20.
 */
public class MyApplication extends MultiDexApplication {

    public static MyApplication mContext;//主线程上下文
    public Vibrator mVibrator;
    private static Toast mToast = null;//全局Toast
    @Override
    public void onCreate() {
        super.onCreate();
        /***
         * 初始化定位sdk
         */
        mContext = this;
//        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
//        SDKInitializer.initialize(getApplicationContext());

    }

    public static void showToast(String text) {
        if (text.isEmpty()) {
            return;
        } else if (mToast == null) {
            mToast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }

        mToast.show();
    }
}
