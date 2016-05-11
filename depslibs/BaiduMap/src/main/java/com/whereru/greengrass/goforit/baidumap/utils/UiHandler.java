package com.whereru.greengrass.goforit.baidumap.utils;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.baidu.mapapi.model.LatLng;

import java.util.HashSet;
import java.util.concurrent.ConcurrentMap;


/**
 * Created by lulei on 16/5/10.
 */
public class UiHandler {
    /**
     * 地理位置更新消息类型
     */
    public static final int MSG_UPDATE_CURRENT_LOCATION = 0x000_000_001;
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

    public final static boolean post(Runnable r) {
        if (uiHandler == null) {
            return false;
        }
        uiHandler.removeCallbacks(r);
        return uiHandler.post(r);
    }

    public final static boolean postDelayed(Runnable r, long delayMillis) {
        if (uiHandler == null) {
            return false;
        }
        uiHandler.removeCallbacks(r);
        return uiHandler.postDelayed(r, delayMillis);
    }

    public final static Handler getUiHandler() {
        return uiHandler;
    }

    public final static boolean postOnceDelayed(Runnable r, long delayMillis) {
        if (uiHandler == null) {
            return false;
        }
        uiHandler.removeCallbacks(r);
        return uiHandler.postDelayed(r, delayMillis);
    }

    public static void removeCallbacks(Runnable runnable) {
        if (uiHandler == null) {
            return;
        }
        uiHandler.removeCallbacks(runnable);
    }

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
