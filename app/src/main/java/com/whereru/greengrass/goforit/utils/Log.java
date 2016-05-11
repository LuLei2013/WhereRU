package com.whereru.greengrass.goforit.utils;

/**
 * Created by didi on 16/5/10.
 */
public class Log {
    private static final String TAG = "APP_TAG_RUBY";

    public static void e(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.e(TAG, info);
        }

    }

    public static void i(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.i(TAG, info);
        }
    }

    public static void v(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.v(TAG, info);
        }
    }

    public static void w(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.w(TAG, info);
        }
    }

    public static void d(String info) {
        if (Util.IS_DEBUG) {
            android.util.Log.d(TAG, info);
        }
    }
}
