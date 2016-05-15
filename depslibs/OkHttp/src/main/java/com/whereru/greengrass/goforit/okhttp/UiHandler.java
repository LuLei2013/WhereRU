package com.whereru.greengrass.goforit.okhttp;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * okhttp请求的response是在子线程中回调的,为了使用请求结果更新UI,
 * 必须将请求的结果传到UI线程中,该类负责将已经预处理{@link com.whereru.greengrass.goforit.okhttp.parser.Parser 及其子类处理}
 * 的请求结果通过回调的方式告知调用者
 * <p>
 * Created by lulei on 16/5/9.
 */
public class UiHandler extends Handler {
    private static UiHandler mInstance;

    private UiHandler() {
        super(Looper.getMainLooper());
    }

    public static UiHandler getInstance() {
        if (mInstance == null) {
            synchronized (UiHandler.class) {
                if (mInstance == null) {
                    mInstance = new UiHandler();
                }
            }
        }
        return mInstance;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 0x00_001:
                if (msg != null && msg.obj != null) {
                    Log.e("上报坐标后,服务器返回的信息:" + msg.obj.toString());
                }
                break;
        }

        super.handleMessage(msg);
    }
}
