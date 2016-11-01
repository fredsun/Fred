package com.fred.activity;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fred.R;
import com.fred.base.BaseActivity;
import com.fred.utils.LoggerManager;
import com.fred.wheelview.OnWheelChangedListener;
import com.fred.wheelview.WheelView;
import com.fred.wheelview.adapter.ArrayWheelAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/10/27.
 */

public class CitywheelActivity extends BaseActivity implements OnWheelChangedListener {
    /**
     * 把全国的省市区的信息以json的格式保存，解析完成后赋值为null
     */
    private JSONObject mJsonObj;
    /**
     * 省的WheelView控件
     */
    private WheelView mProvince;
    /**
     * 市的WheelView控件
     */
    private WheelView mCity;
    /**
     * 区的WheelView控件
     */
    private WheelView mArea;

    /**
     * 所有省
     */
    private String[] mProvinceDatas;
    /**
     * key - 省 value - 市s
     */
    private Map<String, String[]> mCityDataMap = new HashMap<>();
    /**
     * key - 市 values - 区s
     */
    private Map<String, String[]> mAreaDataMap = new HashMap<>();

    /**
     * 当前省的名称
     */
    private String mCurrentProvinceName = null;
    /**
     * 当前市的名称
     */
    private String mCurrentCityName = null;
    /**
     * 当前区的名称
     */
    private String mCurrentAreaName = null;

    TextView tv_city_wheel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citywheel);
        tv_city_wheel = (TextView) findViewById(R.id.tv_city_wheel);

    }

    public void onClickCity(View view){
        switch (view.getId()){
            case R.id.tv_city_wheel:
                showPopCIty();
                break;
        }
    }

    /**
     * 显示city 的popupWindow
     */
    public void showPopCIty() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentview = inflater.inflate(R.layout.person_city, null);

        contentview.setFocusable(true); // 这个很重要
        contentview.setFocusableInTouchMode(true);
        final PopupWindow popupWindow = new PopupWindow(contentview,
                android.view.ViewGroup.LayoutParams.FILL_PARENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.setOutsideTouchable(true);
        popupWindow.getContentView().setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });
        initCity(contentview);
        Button determine = (Button) contentview.findViewById(R.id.bu_area);
        determine.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (mCurrentProvinceName.equals("北京") || mCurrentProvinceName.equals("上海")
                        || mCurrentProvinceName.equals("重庆") || mCurrentProvinceName.equals("天津")
                        || mCurrentProvinceName.equals("香港") || mCurrentProvinceName.equals("澳门")
                        || mCurrentProvinceName.equals("国外") || mCurrentProvinceName.equals("台湾")) {
                    mCurrentAreaName = null;
                }

                //TODO sendToServer
                if (mCurrentAreaName == null){
                    tv_city_wheel.setText(mCurrentProvinceName+mCurrentCityName);
                }else {
                    tv_city_wheel.setText(mCurrentProvinceName+mCurrentCityName+mCurrentAreaName);
                }

                popupWindow.dismiss();
            }
        });
        popupWindow.showAtLocation(findViewById(R.id.tv_city_wheel),
                Gravity.BOTTOM, 0, 0);
    }

    /**
     * 初始化选择城市控件
     */
    public void initCity(View view) {
        initJsonData();
        mProvince = (WheelView) view.findViewById(R.id.id_province);
        mCity = (WheelView) view.findViewById(R.id.id_city);
        mArea = (WheelView) view.findViewById(R.id.id_area);
        initDatas();
        mProvince.setViewAdapter(new ArrayWheelAdapter<String>(this,
                mProvinceDatas));
        // 添加change事件
        mProvince.addChangingListener(this);
        // 添加change事件
        mCity.addChangingListener(this);
        // 添加change事件
        mArea.addChangingListener(this);

        mProvince.setVisibleItems(5);
        mCity.setVisibleItems(5);
        mArea.setVisibleItems(5);

        updateCities();
        updateAreas();

    }

    /**
     * 从assert文件夹中读取省市区的json文件，然后转化为json对象
     */
    private void initJsonData() {
        try {
            StringBuffer sb = new StringBuffer();
            InputStream is = getAssets().open("city.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = is.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, "gbk"));
            }
            is.close();
            mJsonObj = new JSONObject(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析整个Json对象，完成后释放Json对象的内存
     */
    private void initDatas() {
        try {
            JSONArray jsonArray = mJsonObj.getJSONArray("citylist");
            mProvinceDatas = new String[jsonArray.length()];
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonP = jsonArray.getJSONObject(i);// 每个省的json对象
                String province = jsonP.getString("p");// 省名字

                mProvinceDatas[i] = province;

                JSONArray jsonCs;
                try {
                    /**
                     * Throws Exception if the mapping doesn't exist or is
                     * not a JSONArray.
                     */
                    jsonCs = jsonP.getJSONArray("c");
                } catch (Exception e1) {
                    continue;
                }
                String[] mCitiesDatas = new String[jsonCs.length()];
                for (int j = 0; j < jsonCs.length(); j++) {
                    JSONObject jsonCity = jsonCs.getJSONObject(j);
                    String city = jsonCity.getString("n");// 市名字
                    mCitiesDatas[j] = city;
                    JSONArray jsonAreas;
                    try {
                        /**
                         * Throws Exception if the mapping doesn't exist or
                         * is not a JSONArray.
                         */
                        jsonAreas = jsonCity.getJSONArray("a");
                    } catch (Exception e) {
                        continue;
                    }

                    String[] mAreasDatas = new String[jsonAreas.length()];// 当前市的所有区
                    for (int k = 0; k < jsonAreas.length(); k++) {
                        String area = jsonAreas.getJSONObject(k).getString("s");// 区域的名称
                        mAreasDatas[k] = area;
                    }
                    mAreaDataMap.put(city, mAreasDatas);
                }

                mCityDataMap.put(province, mCitiesDatas);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        mJsonObj = null;
    }

    /**
     * 根据当前的市，更新区WheelView的信息
     */
    private void updateAreas() {
        int pCurrent = mCity.getCurrentItem();
        mCurrentCityName = mCityDataMap.get(mCurrentProvinceName)[pCurrent];
        String[] areas = mAreaDataMap.get(mCurrentCityName);

        if (areas == null) {
            areas = new String[]{""};
        }
        mArea.setViewAdapter(new ArrayWheelAdapter<>(this, areas));
        mArea.setCurrentItem(0);
        if (mAreaDataMap.get(mCurrentCityName) != null) {
            mCurrentAreaName = mAreaDataMap.get(mCurrentCityName)[0];
        }
    }

    /**
     * 根据当前的省，更新市WheelView的信息
     */
    private void updateCities() {

        int pCurrent = mProvince.getCurrentItem();
        mCurrentProvinceName = mProvinceDatas[pCurrent];
        String[] cities = mCityDataMap.get(mCurrentProvinceName);
        if (cities == null) {
            cities = new String[]{""};
        }
        mCity.setViewAdapter(new ArrayWheelAdapter<>(this, cities));
        mCity.setCurrentItem(0);
        updateAreas();
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        if (wheel == mProvince) {
            updateCities();
        } else if (wheel == mCity) {
            updateAreas();
        } else if (wheel == mArea) {
            LoggerManager.i(mAreaDataMap.get(mCurrentCityName)[newValue]);
            if (mAreaDataMap.get(mCurrentCityName)[newValue] != null) {
                mCurrentAreaName = mAreaDataMap.get(mCurrentCityName)[newValue];
            } else {
                LoggerManager.i("没有选中");
            }
        }
    }
}
