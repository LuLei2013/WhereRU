package com.whereru.greengrass.goforit.baidumap;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.baidu.mapapi.model.LatLng;
import com.whereru.greengrass.goforit.baidumap.utils.Log;

import java.util.HashSet;
import java.util.concurrent.ConcurrentMap;


/**
 * 该类只能运行在应用进程中
 * <p/>
 * <p/>
 * Created by lulei on 16/5/10.
 */
public class UiHandler {

    static {
        Log.i("com.whereru.greengrass.goforit.baidumap.UiHandler has been init<static> in" + android.os.Process.myPid());
    }

    /**
     * 地理位置更新消息类型
     */
    public static final int MSG_UPDATE_CURRENT_LOCATION = 0x000_0_001;
    /**
     * 地理位置变化的所有观察者
     */
    private static HashSet<LocationChangeListener> mCallBacks = new HashSet<>();

    private static Handler uiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }
            switch (msg.what) {
                case MSG_UPDATE_CURRENT_LOCATION:
                    synchronized (mCallBacks) {
                        for (LocationChangeListener locationChangeListener : mCallBacks) {
                            locationChangeListener.onLocationChanged((LatLng) msg.obj);
                        }
                    }
                    break;

            }
            super.handleMessage(msg);
        }
    };


    public static void sendMessage(Message msg) {
        if (uiHandler == null) {
            return;
        }
        uiHandler.sendMessage(msg);
    }

    public static void removeLocationMessages() {
        uiHandler.removeMessages(MSG_UPDATE_CURRENT_LOCATION);
    }

    public interface LocationChangeListener {
        public void onLocationChanged(LatLng coordinate);
    }

    public static void registerLocationChangedListener(LocationChangeListener locationChangeListener) {
        synchronized (mCallBacks) {
            mCallBacks.add(locationChangeListener);
        }
    }

    public static void unregisterLocationChangedListener(LocationChangeListener locationChangeListener) {
        synchronized (mCallBacks) {
            mCallBacks.remove(locationChangeListener);
        }
    }
}
