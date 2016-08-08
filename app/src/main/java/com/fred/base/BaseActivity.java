package com.fred.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.baidu.mtjstatsdk.StatSDKService;
import com.fred.utils.LoggerManager;
import com.fred.utils.MyActivityList;

/**
 * Created by Administrator on 2016/7/18.
 */
public class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyActivityList.getInstance().addActivity(this);
        LoggerManager.i(getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyActivityList.getInstance().delActivity(this);
    }
}
