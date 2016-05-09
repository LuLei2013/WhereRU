package com.whereru.greengrass.goforit.baidupush.receiver;

import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by didi on 16/5/9.
 */
public class PushMessageReceiver extends com.baidu.android.pushservice.PushMessageReceiver {

    @Override
    public void onBind(Context context, int i, String s, String s1, String s2, String s3) {
        Log.e("Ruby", "onBind i :" + i + " , s :" + s + "  ,s1 :" + s1 + "  ,s2 :" + s2 + "  ,s3 :" + s3);
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
    public void onMessage(Context context, String s, String s1) {
        Log.e("Ruby", "onMessage s : " + s + " ,s1 " + s1);
    }

    @Override
    public void onNotificationClicked(Context context, String s, String s1, String s2) {

    }

    @Override
    public void onNotificationArrived(Context context, String s, String s1, String s2) {

    }
}
