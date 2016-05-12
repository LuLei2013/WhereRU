package com.whereru.greengrass.goforit.baidupush;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.whereru.greengrass.goforit.baidupush.entity.PushMessage;

import java.util.HashMap;

/**
 * Created by lulei on 16/5/11.
 */
public class UiHandler {
    /**
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
     * push各种消息的回调,K为消息类型,V为消息处理回调
     */
    private static HashMap<Integer, PushMessageComingLisener> mCallBacks = new HashMap<>();

    private static Handler uiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }
            switch (msg.what) {
                case MSG_RECEIVE_PUSH_GOING_INTO_BUSINISS_MSG:
                    break;
                case MSG_RECEIVE_PUSH_GOING_OUT_BUSINISS_MSG:
                    break;
                case MSG_RECEIVE_PUSH_NEW_USER_BUSINISS_MSG:
                    break;
                case MSG_RECEIVE_PUSH_SWITCH_BUSINISS_MSG:
                    break;
                case MSG_RECEIVE_PUSH_PUSH_AD_BUSINISS_MSG:
                    break;

            }
            super.handleMessage(msg);
        }
    };

    public final static boolean post(Runnable r) {
        if (uiHandler == null) {
            return false;
        }
        uiHandler.removeCallbacks(r);
        return uiHandler.post(r);
    }

    public final static boolean postDelayed(Runnable r, long delayMillis) {
        if (uiHandler == null) {
            return false;
        }
        uiHandler.removeCallbacks(r);
        return uiHandler.postDelayed(r, delayMillis);
    }

    public final static Handler getUiHandler() {
        return uiHandler;
    }

    public final static boolean postOnceDelayed(Runnable r, long delayMillis) {
        if (uiHandler == null) {
            return false;
        }
        uiHandler.removeCallbacks(r);
        return uiHandler.postDelayed(r, delayMillis);
    }

    public static void removeCallbacks(Runnable runnable) {
        if (uiHandler == null) {
            return;
        }
        uiHandler.removeCallbacks(runnable);
    }

    public static void sendMessage(Message msg) {
        if (uiHandler == null) {
            return;
        }
        uiHandler.sendMessage(msg);
    }

    public static void removePushMessages(int msgType) {
        uiHandler.removeMessages(msgType);
    }


    public static void registerPushMessageComingLisener(int msgtype, PushMessageComingLisener pushMessageComingLisener) {
        synchronized (mCallBacks) {
            mCallBacks.put(msgtype, pushMessageComingLisener);
        }
    }

    public static void unregisterPushMessageComingLisener(int msgType) {
        synchronized (mCallBacks) {
            mCallBacks.remove(msgType);
        }
    }

    /**
     * Created by lulei on 16/5/11.
     * push消息到达时,回调接口
     */
    public interface PushMessageComingLisener {
        public void onMessageComing(PushMessage msg);
    }

}
