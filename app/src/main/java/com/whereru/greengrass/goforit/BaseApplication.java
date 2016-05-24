package com.whereru.greengrass.goforit;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.whereru.greengrass.goforit.activity.BusinessComingActivity;
import com.whereru.greengrass.goforit.baidupush.PushManager;
import com.whereru.greengrass.goforit.baidumap.manager.LocationManager;
import com.whereru.greengrass.goforit.baidumap.manager.MapManager;
import com.whereru.greengrass.goforit.commonmodule.EventManager;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.PushMessage;
import com.whereru.greengrass.goforit.commonmodule.utils.Log;
import com.whereru.greengrass.goforit.commonmodule.utils.Util;
import com.whereru.greengrass.goforit.service.UploadLocationService;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
            Log.e("@BaseApplication#onCreate,应用启动~");
            sApplicatonContext = getApplicationContext();
            //启动百度推送
            PushManager.startPush(getApplicationContext());
            //启动百度定位
            LocationManager.getInstance(this).startLocation();
            //启动百度地图
            MapManager.getInstance(this).startMap();
            //注册为处理EventBus入口
            EventManager.getInstance().register(this);
            // 启动上传位置信息服务
            startService(new Intent(this, UploadLocationService.class));

        }
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (getPackageName().equals(Util.getCurProcessName(getApplicationContext()))) {
            Log.e("@BaseApplication#onTerminate,应用退出~");
            //关闭百度推送
            PushManager.stopPush(getApplicationContext());
            //关闭百度定位
            LocationManager.getInstance(this).stopLocation();
            //反注销为处理EventBus入口
            EventManager.getInstance().unregister(this);
            // 关闭上传位置信息服务
            stopService(new Intent(this, UploadLocationService.class));
            sApplicatonContext = null;

        }
    }


    /**
     * EventBus PushMessage  分发入口,Push 消息统一先进入MainActvity,
     * 然后由MainActivity 打开目标Activity
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handlePushMessage(PushMessage message) {
        Log.e("@BaseApplication#handlePushMessage,收到一个Push消息:" + message.toString());
        if (message == null) {
            return;
        }
        String targetActivityName = null;
        switch (message.getStatus()) {
            // 进入圈子
            // 离开并同时进入圈子
            case 1:
            case 2:
                targetActivityName = BusinessComingActivity.class.getName();
                break;
            //离开圈子
            case 3:
                break;
        }

        startActivity(getIntent(message, targetActivityName));
    }

    private Intent getIntent(PushMessage pushmessage, String targetActivityName) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse(Constants.MAIN_ACTIVITY_SCHEMA));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Intent targetIntent = new Intent(Intent.ACTION_VIEW);
        targetIntent.addCategory(Intent.CATEGORY_DEFAULT);
        targetIntent.setData(Uri.parse(Constants.TARGET_ACTIVITY_SCHEM + Constants.SCHEMA_AND_HOST_SEPORATER + targetActivityName));
        targetIntent.putExtra(Constants.TARGET_ACTIVITY_DATA_KEY, pushmessage);
        intent.putExtra(Constants.TARGET_ACTIVITY, targetIntent);
        return intent;
    }
}
