package com.whereru.greengrass.goforit.baidumap.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;

import com.baidu.mapapi.model.LatLng;

/**
 * Created by lulei on 16/5/9.
 */
public class LocationPreferences {
    public static final String PREFERENCE_NAME = "location_preference";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    private static LocationPreferences mInstance;

    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;

    private LocationPreferences(Context context) {
        mPref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        mEditor = mPref.edit();
    }

    public static LocationPreferences getInstance(Context context) {
        if (mInstance == null) {
            synchronized (LocationPreferences.class) {
                if (mInstance == null) {
                    mInstance = new LocationPreferences(context.getApplicationContext());
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

    public void putDouble(String key, double value) {
        mEditor.putLong(key, Double.doubleToLongBits(value));
        commit();
    }

    public double getDouble(String key, double defValue) {
        return Double.longBitsToDouble(mPref.getLong(key, Double.doubleToLongBits(defValue)));
    }


    public void seLat(double lat) {
        putDouble(LAT, lat);
    }

    public void setLng(double lng) {
        putDouble(LNG, lng);
    }

    public void setLatLng(LatLng latLng) {
        seLat(latLng.latitude);
        setLng(latLng.longitude);
    }

    public double getLat(double defValue) {
        return getDouble(LNG, defValue);
    }

    public double getLng(double defValue) {
        return getDouble(LNG, defValue);
    }

    public LatLng getLatLng(LatLng defValue) {
        return new LatLng(getLat(defValue.latitude), getLng(defValue.longitude));
    }

    public LatLng putLatLng(LatLng defValue) {
        return new LatLng(getLat(defValue.latitude), getLng(defValue.longitude));
    }


}