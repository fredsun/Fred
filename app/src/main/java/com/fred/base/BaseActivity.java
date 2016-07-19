package com.fred.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.baidu.mtjstatsdk.StatSDKService;
import com.fred.utils.LoggerManager;
import com.fred.utils.MyApplication;

/**
 * Created by Administrator on 2016/7/18.
 */
public class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        //百度统计_设置渠道
        StatSDKService.setAppChannel(this,"channelFred1",true,"ead7b6b463");
        //百度统计_设置日志发送延迟时间
        StatSDKService.setLogSenderDelayed(1,"ead7b6b463");
        LoggerManager.i(getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        StatSDKService.onResume(this,"ead7b6b463");
    }

    @Override
    protected void onPause() {
        super.onPause();
        StatSDKService.onPause(this,"ead7b6b463");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApplication.getInstance().delActivity(this);
    }
}
