package com.whereru.greengrass.goforit.commonmodule.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;

/**
 * Created by lulei on 16/5/9.
 */
public class Preferences {
    public static final String PREFERENCE_NAME = "push_preference";
    public static final String APP_ID = "app_id";
    public static final String API_KEY = "api_key";
    public static final String USER_ID = "user_id";
    public static final String CHANNEL_ID = "channel_id";
    public static final String CURRENT_BUSINESS_ID = "current_business_id";
    private static volatile Preferences mInstance;

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    private Preferences(Context context) {
        mPref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public static Preferences getInstance(Context context) {
        if (mInstance == null) {
            synchronized (Preferences.class) {
                if (mInstance == null) {
                    mInstance = new Preferences(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private void commit() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            mEditor.apply();
        } else {
            mEditor.commit();
        }
    }

    public boolean contains(String key) {
        return mPref.contains(key);
    }

    public void putString(String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            mEditor.putString(key, value);
        } else {
            mEditor.remove(key);
        }
        commit();
    }

    public String getString(String key, String defValue) {
        return mPref.getString(key, defValue);
    }


    public void setAppId(String appId) {
        putString(APP_ID, appId);
    }

    public void setApiKey(String apiKey) {
        putString(API_KEY, apiKey);
    }

    public void setUserId(String userId) {
        putString(USER_ID, userId);
    }

    public void setChannelId(String channelId) {
        putString(CHANNEL_ID, channelId);
    }


    public String getApiKey(String defValue) {
        return getString(API_KEY, defValue);
    }

    public String getUserId(String defValue) {
        return getString(USER_ID, defValue);
    }

    public String getAppId(String defValue) {
        return getString(APP_ID, defValue);
    }

    public String getChannelId(String defValue) {
        return getString(CHANNEL_ID, defValue);
    }

    public long getCurrentBusinessId() {
        return mPref.getLong(CURRENT_BUSINESS_ID, -1l);
    }

    public void setCurrentBusinessId(long businessId) {
        mEditor.putLong(CURRENT_BUSINESS_ID, businessId);
        commit();
    }

    static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager mActivityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningAppProcessInfo appProcess : mActivityManager
                .getRunningAppProcesses()) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}