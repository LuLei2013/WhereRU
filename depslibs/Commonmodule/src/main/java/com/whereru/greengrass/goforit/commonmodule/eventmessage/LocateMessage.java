package com.whereru.greengrass.goforit.commonmodule.eventmessage;

import com.baidu.mapapi.model.LatLng;

/**
 * Created by didi on 16/5/23.
 */
public class LocateMessage extends Message {
    private LatLng mLocation;

    public LocateMessage() {
        super(MessageType.LOCATION_MESSAGE_TYPE);
    }

    public LatLng getLocation() {
        return mLocation;
    }

    public void setLocation(LatLng location) {
        this.mLocation = location;
    }

    @Override
    public String toString() {
        return "LocateMessage{" +
                "mLocation=" + mLocation +
                '}';
    }
}
