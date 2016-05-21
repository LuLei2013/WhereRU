package com.whereru.greengrass.goforit.commonmodule.utils;

import java.util.Date;

/**
 * Created by didi on 16/5/10.
 */
public class Log {
    private static final String TAG = "COMMON_LOG_TAG";

    public static void e(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.e(TAG, makeLogInfo(info));
        }

    }

    public static void i(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.i(TAG, makeLogInfo(info));
        }
    }

    public static void v(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.v(TAG, makeLogInfo(info));
        }
    }

    public static void w(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.w(TAG, makeLogInfo(info));
        }
    }

    public static void d(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.d(TAG, makeLogInfo(info));
        }
    }

    private static String makeLogInfo(String info) {
        return new Date().toString() + "\t" + info;
    }
}
