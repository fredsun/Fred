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
        //1.百度统计_设置渠道
        StatSDKService.setAppChannel(this,"channelFred1",true,"ead7b6b463");
        //2.百度统计_设置日志发送延迟时间
        StatSDKService.setLogSenderDelayed(1,"ead7b6b463");
        LoggerManager.i(getClass().getSimpleName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        //3.百度统计添加onResume
        StatSDKService.onResume(this,"ead7b6b463");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //4.百度统计添加onPause
        StatSDKService.onPause(this,"ead7b6b463");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyActivityList.getInstance().delActivity(this);
    }
}
