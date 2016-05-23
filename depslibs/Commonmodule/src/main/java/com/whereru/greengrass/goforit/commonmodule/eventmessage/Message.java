package com.whereru.greengrass.goforit.commonmodule.eventmessage;

import java.io.Serializable;

/**
 * Created by didi on 16/5/23.
 */
public abstract class Message implements Serializable {
    private MessageType msgType;

    public MessageType getMsgType() {
        return msgType;
    }

    Message() {
        this(MessageType.ERROR_MESSAGE_TYPE);
    }

    Message(MessageType messageType) {
        this.msgType = messageType;
    }
}
