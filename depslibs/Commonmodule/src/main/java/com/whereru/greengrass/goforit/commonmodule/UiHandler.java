package com.whereru.greengrass.goforit.commonmodule;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by lulei on 16/5/11.
 */
public class UiHandler {


    /**
     * 收到新Push消息,消息解析出错
     */
    public static final int MSG_RECEIVE_PUSH_ERROR_BUSINISS_MSG = 0x111_1_111;
    /**
     * /**R
     * 收到新的Push消息,消息类型为进入一个商圈
     */
    public static final int MSG_RECEIVE_PUSH_GOING_INTO_BUSINISS_MSG = 0x001_0_001;
    /**
     * 收到新的Push消息,消息类型为离开一个商圈
     */
    public static final int MSG_RECEIVE_PUSH_GOING_OUT_BUSINISS_MSG = 0x001_0_002;

    /**
     * 收到新的Push消息,消息类型为有新人进入当前商圈
     */
    public static final int MSG_RECEIVE_PUSH_NEW_USER_BUSINISS_MSG = 0x001_0_003;

    /**
     * 收到新的Push消息,消息类型为离开当前商圈,直接进入了新的商圈
     */
    public static final int MSG_RECEIVE_PUSH_SWITCH_BUSINISS_MSG = 0x001_0_004;

    /**
     * 收到新的Push消息,消息类型为收到商圈推送的新消息
     */
    public static final int MSG_RECEIVE_PUSH_PUSH_AD_BUSINISS_MSG = 0x001_1_001;

    /**
     * 地理位置更新消息类型
     */
    public static final int MSG_UPDATE_CURRENT_LOCATION = 0x000_0_001;

    private static Handler uiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }

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
