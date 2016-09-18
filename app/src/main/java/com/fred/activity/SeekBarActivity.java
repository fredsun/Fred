package com.fred.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.fred.R;
import com.fred.base.BaseActivity;

/**
 * Created by Administrator on 2016/7/19.
 */
public class SeekBarActivity extends BaseActivity {
    private TextView tv_num,tv_deadline;
    private SeekBar seekBar;
    private double width, xDensity;
    private int barNumbers =0;


    private DisplayMetrics displaysMetrics;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        initView();
        initSeekBarProgress();
    }
    //获取屏幕信息，以及初始化操作
    private void initView() {
        displaysMetrics = getResources().getDisplayMetrics();
        width = displaysMetrics.widthPixels;
        xDensity = (width - dip2px(this, 51)) / 100;
        seekBar = (SeekBar) findViewById(R.id.seekBar1);
        tv_num = (TextView) findViewById(R.id.num_tv);
        tv_deadline = (TextView) findViewById(R.id.tv_deadline);
    }
    //TextView跟随SeekBar移动
    private void initSeekBarProgress() {
        seekBar.setProgress(barNumbers);
        seekBar.setOnSeekBarChangeListener(mSeekChange);
        LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsStrength.leftMargin = (int) (barNumbers * xDensity);
        tv_num.setLayoutParams(paramsStrength);
        tv_num.setText(barNumbers +"");
    }

    private SeekBar.OnSeekBarChangeListener mSeekChange = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            barNumbers = progress;
            LinearLayout.LayoutParams paramsStrength = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
            paramsStrength.leftMargin = (int) (progress * xDensity);
            tv_num.setLayoutParams(paramsStrength);
            tv_num.setText(barNumbers +"");

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            int seekProgress = seekBar.getProgress();
            if(seekProgress<3.3){
                seekBar.setProgress(0);
            }else if(seekProgress>=13 && seekProgress<38){
                seekBar.setProgress(25);
            }else if(seekProgress>=38 && seekProgress<63){
                seekBar.setProgress(50);
            }else if(seekProgress>=63 && seekProgress<88){
                seekBar.setProgress(75);
            }else if(seekProgress>=88){
                seekBar.setProgress(100);
            }
        }

    };

    /**
     * 根据手机分辨率从 px(像素) 单位 转成 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 根据手机分辨率从 dp 单位 转成 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
