package com.whereru.greengrass.goforit.baidupush;

import android.content.Context;

import com.baidu.android.pushservice.PushConstants;


/**
 * Created by lulei on 16/5/12.
 */
public class PushManager {
    public static void startPush(Context context) {
        com.baidu.android.pushservice.PushManager.startWork(context, PushConstants.LOGIN_TYPE_API_KEY, "uSCb0UhENlVZUC3DMUI02LH8FeaNl45H");
    }

    public static void stopPush(Context context) {
        com.baidu.android.pushservice.PushManager.stopWork(context);
    }
}
