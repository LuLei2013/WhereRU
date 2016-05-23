package com.whereru.greengrass.goforit.baidupush.utils;


import android.os.Message;

import com.google.gson.Gson;
import com.whereru.greengrass.goforit.commonmodule.UiHandler;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.ErrorMessage;
import com.whereru.greengrass.goforit.commonmodule.eventmessage.PushMessage;
import com.whereru.greengrass.goforit.commonmodule.utils.GsonJson;

/**
 * Created by lulei on 16/5/11.
 */
public class PushMessageParser {

    public static void parse(String json) {
        PushMessage pushMessage = null;
        Message msg = new Message();
        try {
            Gson gson = GsonJson.getInstance();
            pushMessage = gson.fromJson(json, PushMessage.class);
            msg.obj = pushMessage;
            msg.what = UiHandler.MSG_RECEIVE_PUSH;
        } finally {
            if (pushMessage == null) {
                msg.obj = new ErrorMessage("推送消息格式错误:" + json);
                msg.what = UiHandler.MSG_RECEIVE_ERROR;
            }
            UiHandler.sendMessage(msg);
        }
    }
}
