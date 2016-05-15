package com.whereru.greengrass.goforit.baidupush;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

/**
 * 本地动态注册的广播统一管理器,百度定位使用,运行在应用进程的主线程中
 * <p/>
 * Created by lulei on 16/5/10.
 */
public class ReceiverManager {
    private static ReceiverManager mInstance;

    private Context mContext;

    private ReceiverManager(Context context) {
        if (context == null) {
            return;
        }
        this.mContext = context.getApplicationContext();
    }

    public static ReceiverManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (ReceiverManager.class) {
                if (mInstance == null) {
                    mInstance = new ReceiverManager(context);
                }
            }
        }
        return mInstance;
    }

    public void registerReceiver(IntentFilter intentFilter, BroadcastReceiver receiver) {
        if (mContext == null) {
            return;
        }
        if (intentFilter == null || receiver == null) {
            return;
        }
        mContext.registerReceiver(receiver, intentFilter);
    }

    public void unregisterReceiver(BroadcastReceiver receiver) {
        if (mContext == null) {
            return;
        }
        if (receiver == null) {
            return;
        }
        mContext.unregisterReceiver(receiver);
    }

    // 更新百度定位位置的 ACTION
    public static final String ACTION_UPDATE_LOCATION = "action_update_current_location";
}
