package com.whereru.greengrass.goforit.commonmodule.utils;

import android.app.ActivityManager;
import android.content.Context;

/**
 * Created by lulei on 16/5/9.
 */
public class Util {
    public static final boolean IS_DEBUG = true;
    public static String getCurProcessName(Context context) {
        if (context == null) {
            return null;
        }
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager)
                context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }
}
