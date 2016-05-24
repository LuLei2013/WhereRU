package com.whereru.greengrass.goforit.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.baidu.mapapi.model.LatLng;
import com.whereru.greengrass.goforit.commonmodule.EventManager;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.LocateMessage;
import com.whereru.greengrass.goforit.commonmodule.utils.Log;
import com.whereru.greengrass.goforit.commonmodule.utils.Preferences;
import com.whereru.greengrass.goforit.okhttp.HttpClient;
import com.whereru.greengrass.goforit.okhttp.callback.CallBack;
import com.whereru.greengrass.goforit.okhttp.parser.StringParser;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by lulei on 16/5/12.
 * 该类负责,当坐标变化时,将新的坐标不断上传到服务器
 */
public class UploadLocationService extends Service {
    private static final String LOCATION_LATLNG = "location_latlng";


    @Override
    public void onCreate() {
        super.onCreate();
        register();
        Log.i("@UploadLocationService#onCreate, 已启动~");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregister();
        Log.i("@UploadLocationService#onDestroy,已关闭~");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleLocationChanged(intent);
        return super.onStartCommand(intent, flags, startId);
    }


    private void register() {
        EventManager.getInstance().register(this);
    }

    private void unregister() {
        EventManager.getInstance().unregister(this);
    }

    /**
     * EventBus 收到地理位置变化回调
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void handleLocationMessage(LocateMessage message) {
        Log.i("@UploadLocationService#handleLocationMessage, 收到定位信息:" + (message == null ? "null" : message.toString()));
        if (message == null || message.getLocation() == null) {
            return;
        }
        Intent intent = new Intent(this, this.getClass());
        intent.putExtra(LOCATION_LATLNG, message.getLocation());
        startService(intent);
    }

    private void handleLocationChanged(Intent intent) {
        if (intent == null) {
            Log.i("@UploadLocationService#handleLocationChanged,intent参数为null");
            return;
        }
        LatLng latLng = null;
        try {
            latLng = (LatLng) intent.getParcelableExtra(LOCATION_LATLNG);
        } catch (Exception e) {

        }
        if (latLng == null) {
            Log.e("@UploadLocationService#handleLocationChanged,latLng ==null");
            return;
        }
        String channel_id = Preferences.getInstance(this).getChannelId(null);
        if (TextUtils.isEmpty(channel_id)) {
            Log.i("@UploadLocationService#handleLocationChanged,channel_id为空");
            return;
        }
        long businessId = Preferences.getInstance(this).getCurrentBusinessId();
        HttpClient client = HttpClient.getInstance();

        Request request = new Request.Builder()
                .url("http://172.21.129.155:9099/whereru/updatecoord"
                        + "?user_id=kevin"
                        + "&channel_id=" + channel_id
                        + "&lat=" + latLng.latitude
                        + "&lng=" + latLng.longitude
                        + "&biz_area_id=" + businessId
                )
                .build();
        Log.i("@UploadLocationService#handleLocationChanged,");
        client.newCall(request).enqueue(new CallBack(new StringParser()));
    }
}
