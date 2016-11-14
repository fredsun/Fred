package com.fred.activity;

import com.fred.base.BaseActivity;

/**
 * Created by Fred on 2016/9/12.
 */
public class NearbyActivity extends BaseActivity {
//    MapView mMapView = null;
//    BaiduMap baiduMap = null;
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        SDKInitializer.initialize(getApplicationContext());
//        setContentView(R.layout.activity_nearby);
//        //获取地图控件引用
//        BaiduMapOptions baiduMapOptions = new BaiduMapOptions();
//        baiduMapOptions.zoomGesturesEnabled(false);
//        mMapView = (MapView) findViewById(R.id.bmapView);
//        baiduMap = mMapView.getMap();
//
//        baiduMap.setOnMapStatusChangeListener(new BaiduMap.OnMapStatusChangeListener() {
//            @Override
//            public void onMapStatusChangeStart(MapStatus mapStatus) {
//
//            }
//
//            @Override
//            public void onMapStatusChange(MapStatus mapStatus) {
//
//            }
//
//            @Override
//            public void onMapStatusChangeFinish(MapStatus mapStatus) {
//                getScreenData();
//            }
//        });
//    }
//
//    private void assignMap() {
//        float zoomF = 16.074879f;
//        MapStatus ms = new MapStatus.Builder().zoom(zoomF).build();
//        baiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(ms));
//        mMapView.refreshDrawableState();
////        LatLng centerLatLng= new LatLng(lat,lng);
////        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLngZoom(centerLatLng,zoom);
////        mBaiduMap.animateMapStatus(msu);
////        mMapView.refreshDrawableState();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
//        mMapView.onDestroy();
//    }
//    @Override
//    protected void onResume() {
//        super.onResume();
//        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
//        mMapView.onResume();
//    }
//    @Override
//    protected void onPause() {
//        super.onPause();
//        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
//        mMapView.onPause();
//    }
//
//    /**
//     * 获取屏幕相关数据
//     * @algorithm: (屏幕宽度 * 比例尺宽度) / (2 * 地图等级) = 屏幕宽度/2后转换为现实米数
//     * @tips: 比例值宽度在地图未加载完为0,手动设置MapLevel 15对应的初始值95，防空
//     * @return
//     */
//    public void getScreenData(){
//        int scaleControlViewWidth = mMapView.getScaleControlViewWidth();
//        LoggerManager.i("==比例尺宽度",scaleControlViewWidth);
//        int mapLevel = mMapView.getMapLevel();
//        LoggerManager.i("==mapLevel",mapLevel);
//        if (scaleControlViewWidth == 0)
//        {
//            scaleControlViewWidth = 95;
//        }
//
//        DisplayMetrics metric = new DisplayMetrics();
//        getWindowManager().getDefaultDisplay().getMetrics(metric);
//        int width = metric.widthPixels;     // 屏幕宽度（像素）
//        LoggerManager.i("==屏幕宽度（像素）",width);
//
//        float zoom = baiduMap.getMapStatus().zoom;
//        LoggerManager.i("==zoom",zoom);
//
//        long distance = (width * mapLevel)/(2 * scaleControlViewWidth);//屏幕宽度/2的半径米数
//        LoggerManager.i("==1/2屏宽对应米数",distance);
//    }
//

}

