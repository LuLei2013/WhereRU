package com.whereru.greengrass.goforit;

import android.app.Application;

import com.baidu.android.pushservice.PushConstants;
import com.baidu.android.pushservice.PushManager;
import com.whereru.greengrass.goforit.baidumap.manager.LocationManager;
import com.whereru.greengrass.goforit.baidumap.manager.MapManager;

/**
 * Created by didi on 16/5/9.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //启动百度推送
        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, "uSCb0UhENlVZUC3DMUI02LH8FeaNl45H");
        //启动百度定位
        LocationManager.getInstance(this).startLocation();
        //启动百度地图
        MapManager.getInstance(this).startMap();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        //关闭百度推送
        PushManager.stopWork(getApplicationContext());
        //关闭百度定位
        LocationManager.getInstance(this).stopLocation();
    }

}
