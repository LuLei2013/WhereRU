package com.whereru.greengrass.goforit.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.baidumap.UiHandler;
import com.whereru.greengrass.goforit.ui.BaseActivity;


public class MainActivity extends BaseActivity {


    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Marker mCurrentLoationMarker;
    private MarkerOptions mMarkerOptions;
    UiHandler.LocationChangeListener mLocationChangeListener = new UiHandler.LocationChangeListener() {
        @Override
        public void onLocationChanged(LatLng coordinate) {
            onLocationChange(coordinate);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.mapView);
        mBaiduMap = mMapView.getMap();
        init();
        initWindow();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        UiHandler.unregisterLocationChangedListener(mLocationChangeListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    private void onLocationChange(LatLng coordinate) {
        if (mCurrentLoationMarker != null) {
            //删除之前的位置marker
            mCurrentLoationMarker.remove();
        }
        //定义地图状态
        MapStatus mMapStatus = new MapStatus.Builder()
                .target(coordinate)
                .zoom(17)
                .build();
        //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
        MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
        //改变地图状态
        mBaiduMap.setMapStatus(mMapStatusUpdate);
        //重新标记当前的位置
        mCurrentLoationMarker = (Marker) mBaiduMap.addOverlay(mMarkerOptions.position(coordinate));
    }

    /**
     * 可以优化,使得地图每次进来时进入上一次使用的最终位置
     */

    private void init() {
        // 百度地图支持 3~21 各个级别的缩放,17的比例尺为100m

        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(17));
        //注册地理位置变化的监听器
        UiHandler.registerLocationChangedListener(mLocationChangeListener);
        //构建MarkerOption，用于在地图上添加Marker是设置地理位置参数
        mMarkerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.location));
    }

    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

}


