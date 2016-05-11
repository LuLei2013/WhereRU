package com.whereru.greengrass.goforit.baidupush.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by lulei on 16/5/9.
 */
public class PushPreferences {
    public static final String PREFERENCE_NAME = "push_preference";
    public static final String CLIENT_ID = "client_id";
    public static final String APP_ID = "app_id";
    public static final String API_KEY = "api_key";
    public static final String USER_ID = "user_id";
    public static final String CHANNEL_ID = "channel_id";
    private static PushPreferences mInstance;

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    private PushPreferences(Context context) {
        mPref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public static PushPreferences getInstance(Context context) {
        if (mInstance == null) {
            synchronized (PushPreferences.class) {
                if (mInstance == null) {
                    mInstance = new PushPreferences(context.getApplicationContext());
                }
            }
        }
        return mInstance;
    }

    private void commit() {
        if (android.os.Build.VERSION.SDK_INT >= 9) {
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


    public void setClientId(String clientId) {
        putString(CLIENT_ID, clientId);
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

    public String getClientId(String defValue) {
        return getString(CLIENT_ID, defValue);
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
}