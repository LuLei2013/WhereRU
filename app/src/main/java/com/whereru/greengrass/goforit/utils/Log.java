package com.whereru.greengrass.goforit.utils;

import java.util.Date;

/**
 * Created by didi on 16/5/10.
 */
public class Log {
    private static final String TAG = "APP_TAG_RUBY";

    public static void e(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.e(TAG, new Date() + info + "%" + android.os.Process.myPid());
        }

    }

    public static void i(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.i(TAG, new Date() + info + "%" + android.os.Process.myPid());
        }
    }

    public static void v(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.v(TAG, new Date() + info + "%" + android.os.Process.myPid());
        }
    }

    public static void w(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.w(TAG, new Date() + info + "%" + android.os.Process.myPid());
        }
    }


    public static void d(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.d(TAG, new Date() + info + "%" + android.os.Process.myPid());
        }
    }
}
