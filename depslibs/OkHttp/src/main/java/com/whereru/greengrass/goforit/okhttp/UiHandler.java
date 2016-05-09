package com.whereru.greengrass.goforit.okhttp;

import android.content.Context;
import android.os.Handler;
import android.os.Message;

/**
 *  okhttp请求的response是在子线程中回调的,为了使用请求结果更新UI,
 *  必须将请求的结果传到UI线程中
 *
 * Created by lulei on 16/5/9.
 */
public class UiHandler extends Handler {
    private static UiHandler mInstance;

    private UiHandler(Context context) {
        super(context.getMainLooper());
    }

    public static UiHandler getInstance(Context context) {
        if (mInstance == null) {
            synchronized (UiHandler.class) {
                if (mInstance == null) {
                    mInstance = new UiHandler(context);
                }
            }
        }
        return mInstance;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
    }
}
