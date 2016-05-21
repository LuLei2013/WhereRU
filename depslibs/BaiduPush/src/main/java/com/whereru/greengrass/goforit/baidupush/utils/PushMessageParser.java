package com.whereru.greengrass.goforit.baidupush.utils;


import com.google.gson.Gson;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.PushMessage;
import com.whereru.greengrass.goforit.commonmodule.utils.GsonJson;

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
    }

    private static void sendGoOutBusinessMessage(PushMessage msg) {
    }

    private static void sendSwitchBusinessMessage(PushMessage msg) {
    }

    private static void sendPushErrorMessage(String msg) {
    }
}
