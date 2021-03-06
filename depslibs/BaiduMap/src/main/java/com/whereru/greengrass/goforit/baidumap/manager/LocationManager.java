package com.whereru.greengrass.goforit.baidumap.manager;

import android.content.Context;
import android.os.Message;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.model.LatLng;
import com.whereru.greengrass.goforit.commonmodule.UiHandler;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.LocateMessage;
import com.whereru.greengrass.goforit.commonmodule.utils.Log;

/**
 * 百度定位服务管理,百度定位初始化并启动后,会心跳般的不断以接口回调的方式返回最新的地理位置坐标信息,
 * LocationManager类封装了定位服务管理,使得当地理位置更新时,借助UiHandler向UI线程中产生一个消息,
 * 该消息类型为{@link UiHandler#MSG_UPDATE_LOCATION},然后由UiHandler继续通知关心该事件的
 * 观察者.
 * Created by lulei on 16/5/9.
 */
public final class LocationManager {
    // 默认的定位更新时间间隔
    private static final int DEFAULT_LOCATION_SCAN_SPAN = 5000;

    private static volatile LocationManager mInstance;
    // 该context 为应用主进程的Context
    private Context mContext;
    private LocationClientOption mLocationOption;
    //百度定位代理类
    private LocationClient mLocationClient;
    //百度定位到地理位置后,回调接口
    private BDLocationListener mLocationListener = new BDLocationListener() {
        @Override
        public void onReceiveLocation(BDLocation location) {
            Log.i("收到百度定位回调:" + dumpLocation(location));
            Message msg = new Message();
            msg.what = UiHandler.MSG_UPDATE_LOCATION;
            LocateMessage locateMessage = new LocateMessage();
            locateMessage.setLocation(new LatLng(location.getLatitude(), location.getLongitude()));
            msg.obj = locateMessage;
            UiHandler.sendMessage(msg);
        }
    };


    private LocationManager(Context context) {
        if (context == null) {
            return;
        }
        mContext = context.getApplicationContext();
        mLocationClient = new LocationClient(mContext);
    }

    public static LocationManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MapManager.class) {
                if (mInstance == null) {
                    mInstance = new LocationManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 停用百度定位服务,并移除位置坐标变化时的回调和队列中没来得及处理的坐标更新消息
     */
    public void stopLocation() {
        if (mLocationClient != null) {
            mLocationClient.stop();
            mLocationClient.unRegisterLocationListener(mLocationListener);
        }
    }


    public void startLocation() {
        startLocation(DEFAULT_LOCATION_SCAN_SPAN);
    }

    /**
     * 启动百度定位服务,首先清除消息队列中没来得及处理的坐标更新的消息
     */
    public void startLocation(int locationScanSpan) {
        if (locationScanSpan < 1000) {
            locationScanSpan = DEFAULT_LOCATION_SCAN_SPAN;
        }
        //注册监听函数
        mLocationClient.registerLocationListener(mLocationListener);
        initLocation(locationScanSpan);
        //注册将定位结果分发到应用进程中的广播
        mLocationClient.start();
    }


    /**
     * 设置百度定位 相关的初始化参数
     */
    private void initLocation(int locationScanSpan) {
        mLocationOption = new LocationClientOption();
        //设置高精度
        mLocationOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        //设置返回的定位结果坐标系
        mLocationOption.setCoorType("bd09ll");
        mLocationOption.setScanSpan(locationScanSpan);
        mLocationOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        //可选，默认false,设置是否使用gps
        mLocationOption.setOpenGps(true);
        //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        mLocationOption.setLocationNotify(true);
        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationOption.setIgnoreKillProcess(false);
        //可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        mLocationOption.setLocationNotify(true);
        //可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        mLocationOption.setIsNeedLocationDescribe(true);
        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationOption.setIsNeedLocationPoiList(true);
        mLocationOption.SetIgnoreCacheException(false);
        //可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        mLocationOption.setEnableSimulateGps(false);
        mLocationClient.setLocOption(mLocationOption);
    }


    /**
     * dump出指定坐标位置的详细信息
     *
     * @param location
     */
    private String dumpLocation(BDLocation location) {
        //Receive Location
        StringBuffer sb = new StringBuffer();
        sb.append(location.getLocType());
        sb.append(" latitude : ");
        sb.append(location.getLatitude());
        sb.append(" lontitude : ");
        sb.append(location.getLongitude());
        sb.append(" radius : ");
        sb.append(location.getRadius());
        if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
            sb.append(" speed : ");
            sb.append(location.getSpeed());// 单位：公里每小时
            sb.append(" satellite : ");
            sb.append(location.getSatelliteNumber());
            sb.append(" height : ");
            sb.append(location.getAltitude());// 单位：米
            sb.append(" direction : ");
            sb.append(location.getDirection());// 单位度
            sb.append(" addr : ");
            sb.append(location.getAddrStr());
            sb.append(" describe : ");
            sb.append("gps定位成功");

        } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
            sb.append(" addr : ");
            sb.append(location.getAddrStr());
            //运营商信息
            sb.append(" operationers : ");
            sb.append(location.getOperators());
            sb.append(" describe : ");
            sb.append("网络定位成功");
        } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
            sb.append(" describe : ");
            sb.append("离线定位成功，离线定位结果也是有效的");
        } else if (location.getLocType() == BDLocation.TypeServerError) {
            sb.append(" describe : ");
            sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
        } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
            sb.append(" describe : ");
            sb.append("网络不同导致定位失败，请检查网络是否通畅");
        } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
            sb.append(" describe : ");
            sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
        }
        sb.append("locationdescribe : ");
        sb.append(location.getLocationDescribe());// 位置语义化信息
        return sb.toString();
    }
}
