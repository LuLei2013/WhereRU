package com.whereru.greengrass.goforit.baidupush.utils;

import android.os.Message;

import com.google.gson.Gson;
import com.whereru.greengrass.goforit.baidupush.Log;
import com.whereru.greengrass.goforit.baidupush.UiHandler;
import com.whereru.greengrass.goforit.baidupush.entity.PushMessage;

/**
 * Created by didi on 16/5/11.
 */
public class PushMessageParser {

    public static void parse(String json) {
        try {
            Gson gson = GsonJson.getInstance();
            PushMessage msg = gson.fromJson(json, PushMessage.class);
            if (msg == null) {
                sendPushErrorMessage("Push 推送的消息无法解析! 推送的内容为 :" + json);
                return;
            }
            switch (msg.getStatus()) {
                case 1:
                    sendGoIntoBusinessMessage(msg);
                    break;
                case 2:
                    sendSwitchBusinessMessage(msg);
                    break;
                case 3:
                    sendGoOutBusinessMessage(msg);
                    break;
                default:
                    sendPushErrorMessage("Push 推送的消息无法解析! 推送的内容为 :" + json);
            }
        } catch (Exception e) {
            sendPushErrorMessage("Push 推送的消息无法解析! 推送的内容为 :" + json);
        }

    }

    private static void sendGoIntoBusinessMessage(PushMessage msg) {
        Message goInMsg = new Message();
        goInMsg.what = UiHandler.MSG_RECEIVE_PUSH_GOING_INTO_BUSINISS_MSG;
        goInMsg.obj = msg;
        UiHandler.sendMessage(goInMsg);
    }

    private static void sendGoOutBusinessMessage(PushMessage msg) {
        Message goOutMsg = new Message();
        goOutMsg.what = UiHandler.MSG_RECEIVE_PUSH_GOING_OUT_BUSINISS_MSG;
        goOutMsg.obj = msg;
        UiHandler.sendMessage(goOutMsg);
    }

    private static void sendSwitchBusinessMessage(PushMessage msg) {
        Message switchMsg = new Message();
        switchMsg.what = UiHandler.MSG_RECEIVE_PUSH_SWITCH_BUSINISS_MSG;
        switchMsg.obj = msg;
        UiHandler.sendMessage(switchMsg);
    }

    private static void sendPushErrorMessage(String msg) {
        Message errMsg = new Message();
        errMsg.what = UiHandler.MSG_RECEIVE_PUSH_ERROR_BUSINISS_MSG;
        errMsg.obj = msg;
        UiHandler.sendMessage(errMsg);
    }
}
