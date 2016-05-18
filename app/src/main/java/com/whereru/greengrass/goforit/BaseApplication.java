package com.whereru.greengrass.goforit;

import android.app.Application;
import android.content.Context;

import com.whereru.greengrass.goforit.baidupush.PushManager;
import com.whereru.greengrass.goforit.baidumap.manager.LocationManager;
import com.whereru.greengrass.goforit.baidumap.manager.MapManager;
import com.whereru.greengrass.goforit.utils.Log;
import com.whereru.greengrass.goforit.utils.Util;

/**
 * Created by didi on 16/5/9.
 */
public class BaseApplication extends Application {
    //该实例为应用主进程
    public static Context sApplicatonContext;

    @Override
    public void onCreate() {
        super.onCreate();
        if (getPackageName().equals(Util.getCurProcessName(getApplicationContext()))) {
            Log.e("@BaseApplication#onCreate");
            sApplicatonContext = getApplicationContext();
            //启动百度推送
            PushManager.startPush(getApplicationContext());
            //启动百度定位
            LocationManager.getInstance(this).startLocation();
            //启动百度地图
            MapManager.getInstance(this).startMap();
            //注册不断上报地理位置的监听
            UploadLocation.startUploadLocation(this);
        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (getPackageName().equals(Util.getCurProcessName(getApplicationContext()))) {
            Log.e("BaseApplication onTerminate has been called." + android.os.Process.myPid());
            //关闭百度推送
            PushManager.stopPush(getApplicationContext());
            //关闭百度定位
            LocationManager.getInstance(this).stopLocation();
            //关闭不断上报地理位置的监听
            UploadLocation.stopUploadLocation();
            sApplicatonContext = null;
        }
    }
}
