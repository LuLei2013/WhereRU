package com.whereru.greengrass.goforit.commonmodule;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.whereru.greengrass.goforit.commonmodule.eventmessage.LocateMessage;
import com.whereru.greengrass.goforit.commonmodule.utils.Log;


/**
 * 该类只是起到一个事件缓存左右,后面结合EventBus可以将它定义运行在非主线程中
 * Created by lulei on 16/5/17.
 */
public class UiHandler {
    /**
     * 错误消息
     */
    public static final int MSG_RECEIVE_ERROR = 0x111_1_111;


    /**
     * Push消息类型
     */
    public static final int MSG_RECEIVE_PUSH = 0x00_1_001;

    /**
     * 地理位置更新消息类型
     */
    public static final int MSG_UPDATE_LOCATION = 0x000_0_001;

    private static Handler uiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }
            Log.i("UiHandler 收到消息:" + msg.toString());
            EventManager.getInstance().post(msg.obj);
        }
    };

    public static Handler getInstance() {
        return uiHandler;
    }

    public static void sendMessage(Message msg) {
        if (uiHandler == null) {
            return;
        }
        uiHandler.sendMessage(msg);
    }


}
