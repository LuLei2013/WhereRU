package com.whereru.greengrass.goforit.baidupush.receiver;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/**
 * Created by lulei on 16/5/9.
 */
public class PushPreferences {
    private static final String PREFERENCE_NAME = "push_preference";
    private static final String CLIENT_ID = "client_id";
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
                    mInstance = new PushPreferences(context);
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

    public boolean has(String key) {
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

    public int getInt(String key, int defValue) {
        return mPref.getInt(key, defValue);
    }

    public void putInt(String key, int value) {
        mEditor.putInt(key, value);
        commit();
    }

    public float getFloat(String key, float defValue) {
        return mPref.getFloat(key, defValue);
    }

    public void putFloat(String key, float value) {
        mEditor.putFloat(key, value);
        commit();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPref.getBoolean(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        mEditor.putBoolean(key, value);
        commit();
    }

    public long getLong(String key, long defValue) {
        return mPref.getLong(key, defValue);
    }

    public void putLong(String key, long value) {
        mEditor.putLong(key, value);
        commit();
    }

}