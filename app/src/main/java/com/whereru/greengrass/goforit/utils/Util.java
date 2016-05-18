/**
 * Created by didi on 16/5/7.
 */
package com.whereru.greengrass.goforit.utils;

import android.app.ActivityManager;
import android.content.Context;

public class Util {
    final static public boolean IS_DEBUG = true;

    // 获取当前进程名

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
