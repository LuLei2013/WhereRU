package com.whereru.greengrass.goforit.commonmodule.eventmessage;

/**
 * Created by didi on 16/5/23.
 */
public enum MessageType {
    //错误 消息类型
    ERROR_MESSAGE_TYPE,
    //Push 消息类型
    PUSH_MESSAGE_TYPE,
    //Locate 消息类型
    LOCATION_MESSAGE_TYPE,
    //网络请求响应消息类型,上报坐标信息应答
    NET_WORKS_RESPONSE_UPLOAD_LOCATION

}
