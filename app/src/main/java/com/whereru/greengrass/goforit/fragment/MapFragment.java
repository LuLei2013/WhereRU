package com.whereru.greengrass.goforit.fragment;

import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import com.whereru.greengrass.goforit.commonmodule.EventManager;
import com.whereru.greengrass.goforit.commonmodule.UiHandler;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.LocateMessage;
import com.whereru.greengrass.goforit.ui.BaseFragment;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by didi on 16/5/18.
 */
public class MapFragment extends BaseFragment {
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    private Marker mCurrentLoationMarker;
    private MarkerOptions mMarkerOptions;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        register();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View mapFragmentView = inflater.inflate(R.layout.layout_map_fragment, container, false);
        if (mapFragmentView == null) {
            return mapFragmentView;
        }
        //获取地图控件引用
        mMapView = (MapView) mapFragmentView.findViewById(R.id.map_fragment_map);
        mBaiduMap = mMapView.getMap();
        init();
        return mapFragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
        unregister();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
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
        //构建MarkerOption，用于在地图上添加Marker是设置地理位置参数
        mMarkerOptions = new MarkerOptions()
                .icon(BitmapDescriptorFactory
                        .fromResource(R.mipmap.location));
    }

    private void register() {
        EventManager.getInstance().register(this);
    }

    private void unregister() {
        EventManager.getInstance().unregister(this);
    }

    /**
     * EventBus 统一入口
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleLocationMessage(LocateMessage message) {
        if (message == null) {
            return;
        }
        onLocationChange(message.getLocation());
    }
}
