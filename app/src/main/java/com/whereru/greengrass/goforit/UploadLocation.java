package com.whereru.greengrass.goforit;

import android.content.Context;
import android.text.TextUtils;

import com.baidu.mapapi.model.LatLng;
import com.whereru.greengrass.goforit.commonmodule.utils.Log;
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

    public void onLocationChanged(LatLng coordinate) {
        String channel_id = PushPreferences.getInstance(context).getChannelId(null);
        if (TextUtils.isEmpty(channel_id)) {
            return;
        }
        long businessId = PushPreferences.getInstance(context).getCurrentBusinessId();
        Log.e("upload business ID:" + businessId + "." + android.os.Process.myPid());
        HttpClient client = HttpClient.getInstance();
        // 估计有缓存
        Request request = new Request.Builder()
                .url("http://172.21.129.135:9099/whereru/updatecoord"
                        + "?user_id=kevin"
                        + "&channel_id=" + channel_id
                        + "&lat=" + coordinate.latitude
                        + "&lng=" + coordinate.longitude
                        + "&biz_area_id=" + businessId
                )
                .build();
        client.newCall(request).enqueue(new CallBack(new StringParser()));
    }


}
