package com.whereru.greengrass.goforit.commonmodule.eventmessage;


/**
 * Created by didi on 16/5/23.
 */
public class ErrorMessage extends Message {
    private String mErrorInfo;

    public ErrorMessage() {
        this(null);
    }

    public ErrorMessage(String errorInfo) {
        super(MessageType.ERROR_MESSAGE_TYPE);
        this.mErrorInfo = errorInfo;
    }

    @Override
    public String toString() {
        return "ErrorMessage{" +
                "mErrorInfo='" + mErrorInfo + '\'' +
                '}';
    }
}
