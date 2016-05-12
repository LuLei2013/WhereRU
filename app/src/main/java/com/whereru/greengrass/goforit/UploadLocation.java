package com.whereru.greengrass.goforit;

import com.baidu.mapapi.model.LatLng;
import com.whereru.greengrass.goforit.baidumap.UiHandler;
import com.whereru.greengrass.goforit.utils.Log;


import java.io.IOException;
import java.util.Date;

import okhttp3.Call;
import okhttp3.Callback;
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
    private static final UiHandler.LocationChangeListener handler = new UiHandler.LocationChangeListener() {
        @Override
        public void onLocationChanged(LatLng coordinate) {

            try {
                Request request = new Request.Builder()
                        .url("http://172.21.109.17:9090/whereru/updatecoord?lat=" + coordinate.latitude + "&lng=" + coordinate.longitude)
                        .build();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        if (!response.isSuccessful())
                            throw new IOException("Unexpected code " + response);
                        Headers responseHeaders = response.headers();
                        for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                            Log.e(responseHeaders.name(i) + ": " + responseHeaders.value(i));
                        }
                        Log.e(new Date(System.currentTimeMillis()) + response.body().string());
                    }
                });


            } catch (Exception e) {
                Log.e(new Date(System.currentTimeMillis()) + "request to test server error" + e.fillInStackTrace());
            }
        }
    };

    public static void registerUpLoactionCallBack() {
        UiHandler.registerLocationChangedListener(handler);
    }

    public static void unregisterUpLoactionCallBack() {
        UiHandler.unregisterLocationChangedListener(handler);
    }
}
