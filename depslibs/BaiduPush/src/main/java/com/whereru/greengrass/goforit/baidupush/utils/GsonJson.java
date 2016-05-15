package com.whereru.greengrass.goforit.baidupush.utils;

import com.google.gson.Gson;

/**
 * Created by didi on 16/5/12.
 */
public class GsonJson {
    private GsonJson() {
    }

    private static Gson mInstance;

    public static Gson getInstance() {
        if (mInstance == null) {
            synchronized (GsonJson.class) {
                if (mInstance == null) {
                    mInstance = new Gson();
                }
            }
        }
        return mInstance;
    }
}
