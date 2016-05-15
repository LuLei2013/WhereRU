package com.whereru.greengrass.goforit.baidupush;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import com.whereru.greengrass.goforit.baidupush.entity.PushMessage;
import com.whereru.greengrass.goforit.baidupush.utils.PushPreferences;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by lulei on 16/5/11.
 */
public class UiHandler {

    static {
        Log.i("com.whereru.greengrass.goforit.baidupush.UiHandler has been init<static> in" + android.os.Process.myPid());
    }

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
     * push各种消息的回调,K为消息类型,V为消息处理回调
     */
    private static ConcurrentMap<Integer, PushMessageComingLisener> mCallBacks = new ConcurrentHashMap<>();

    private static Handler uiHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg == null) {
                return;
            }
            PushMessageComingLisener callBack = mCallBacks.get(msg.what);
            if (callBack != null) {
                if (MSG_RECEIVE_PUSH_ERROR_BUSINISS_MSG == msg.what) {
                    handleError((String) msg.obj);
                } else {
                    callBack.onMessageComing((PushMessage) msg.obj);
                }
            } else {
                super.handleMessage(msg);
            }
        }


    };


    public static void sendMessage(Message msg) {
        if (uiHandler == null) {
            return;
        }
        uiHandler.sendMessage(msg);
    }

    private static void handleError(String errorMsg) {
        Log.e(errorMsg);
    }

    public static void registerPushMessageComingLisener(Integer msgtype, PushMessageComingLisener pushMessageComingLisener) {
        mCallBacks.put(msgtype, pushMessageComingLisener);
    }

    public static void unregisterPushMessageComingLisener(int msgType) {
        mCallBacks.remove(msgType);
    }

    /**
     * Created by lulei on 16/5/11.
     * push消息到达时,回调接口
     */
    public interface PushMessageComingLisener {
        public void onMessageComing(PushMessage msg);
    }

}
