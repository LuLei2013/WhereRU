package com.whereru.greengrass.goforit.baidupush.receiver;

import android.content.Context;

import com.whereru.greengrass.goforit.baidupush.Log;
import com.whereru.greengrass.goforit.baidupush.Util;
import com.whereru.greengrass.goforit.baidupush.utils.PushMessageParser;
import com.whereru.greengrass.goforit.baidupush.utils.PushPreferences;

import java.util.List;

/**
 * Created by lulei on 16/5/9.
 */
public class PushMessageReceiver extends com.baidu.android.pushservice.PushMessageReceiver {

    @Override
    public void onBind(Context context, int errorCode, String appId, String userId, String channelId, String requestId) {
        Log.e("onBind  errorCode:" + errorCode + " , appId :" + appId + "  ,userId :" + userId + "  ,channelId:" + channelId + "  ,requestId :" + requestId);
        if (errorCode == 0 && !PushPreferences.getInstance(context).contains(PushPreferences.CHANNEL_ID)) {
            updatePushParas(context, appId, userId, channelId);
        }

    }


    @Override
    public void onUnbind(Context context, int i, String s) {

    }

    @Override
    public void onSetTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String rawData, String s1) {
        Log.e("onMessage rawData : " + rawData + " ,s1 " + s1);
        PushMessageParser.parse(rawData);
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {

    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {

    }

    private void updatePushParas(Context context, String appId, String userId, String channelId) {
        PushPreferences.getInstance(context).setAppId(appId);
        PushPreferences.getInstance(context).setUserId(userId);
        PushPreferences.getInstance(context).setChannelId(channelId);
    }
}
