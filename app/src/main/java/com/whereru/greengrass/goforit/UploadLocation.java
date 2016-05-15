package com.whereru.greengrass.goforit;

import android.content.Context;
import android.text.TextUtils;

import com.baidu.mapapi.model.LatLng;
import com.whereru.greengrass.goforit.baidumap.UiHandler;
import com.whereru.greengrass.goforit.baidupush.Log;
import com.whereru.greengrass.goforit.baidupush.utils.PushPreferences;
import com.whereru.greengrass.goforit.okhttp.HttpClient;
import com.whereru.greengrass.goforit.okhttp.callback.CallBack;
import com.whereru.greengrass.goforit.okhttp.parser.StringParser;


import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lulei on 16/5/12.
 * 该类负责,当坐标变化时,将新的坐标不断上传到服务器
 */
public class UploadLocation {
    private static Context context;
    private static final OkHttpClient client = new OkHttpClient();
    private static final UiHandler.LocationChangeListener licationChangedListenter = new UiHandler.LocationChangeListener() {
        @Override
        public void onLocationChanged(LatLng coordinate) {
            String channel_id = PushPreferences.getInstance(context).getChannelId(null);
            if (TextUtils.isEmpty(channel_id)) {
                return;
            }
            long businessId = PushPreferences.getInstance(context).getCurrentBusinessId();
            Log.e("upload business ID:" + businessId + "." +android.os.Process.myPid());
            HttpClient client = HttpClient.getInstance();
            // 估计有缓存
            Request request = new Request.Builder()
                    .url("http://192.168.1.102:9099/whereru/updatecoord"
                            + "?user_id=kevin"
                            + "&channel_id=" + channel_id
                            + "&lat=" + coordinate.latitude
                            + "&lng=" + coordinate.longitude
                            + "&biz_area_id=" + businessId
                    )
                    .build();
            client.newCall(request).enqueue(new CallBack(new StringParser()));
        }
    };

    private static void registerUpLoactionCallBack() {
        UiHandler.registerLocationChangedListener(licationChangedListenter);
    }

    public static void stopUploadLocation() {
        UiHandler.unregisterLocationChangedListener(licationChangedListenter);
    }

    public static void startUploadLocation(Context cxt) {
        context = cxt;
        registerUpLoactionCallBack();
    }
}
