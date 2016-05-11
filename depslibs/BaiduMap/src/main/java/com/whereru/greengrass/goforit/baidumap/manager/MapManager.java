package com.whereru.greengrass.goforit.baidumap.manager;

import android.content.Context;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by didi on 16/5/10.
 */
public class MapManager {

    private static MapManager mInstance;

    private Context mContext;

    private MapManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static MapManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (MapManager.class) {
                if (mInstance == null) {
                    mInstance = new MapManager(context);
                }
            }
        }
        return mInstance;
    }

    public void startMap() {
        if (mContext == null) {
            return;
        }
        SDKInitializer.initialize(mContext.getApplicationContext());
    }
}
