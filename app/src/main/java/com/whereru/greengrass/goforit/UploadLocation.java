package com.whereru.greengrass.goforit;

import com.baidu.mapapi.model.LatLng;
import com.whereru.greengrass.goforit.baidumap.UiHandler;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by lulei on 16/5/12.
 * 该类负责,当坐标变化时,将新的坐标不断上传到服务器
 */
public class UploadLocation {
    private static final OkHttpClient client = new OkHttpClient();

    public static void registerUpLoactionCallBack() {
        UiHandler.registerLocationChangedListener(new UiHandler.LocationChangeListener() {
            @Override
            public void onLocationChanged(LatLng coordinate) {
                Request request = new Request.Builder()
                        .url("172.21.109.17:825739/whereru/updatecoord")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    if (!response.isSuccessful()) {
                        return;
                    }

                    Headers responseHeaders = response.headers();
                    for (int i = 0; i < responseHeaders.size(); i++) {
                        System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                    }

                    System.out.println(response.body().string());

                } catch (Exception e) {

                }
            }
        });
    }
}
