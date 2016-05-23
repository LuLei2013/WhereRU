package com.whereru.greengrass.goforit.commonmodule;


import com.whereru.greengrass.goforit.commonmodule.utils.Log;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by didi on 16/5/21.
 */
public class EventManager {

    private EventBus mEventBus;

    public void post(Object message) {
        if (mEventBus == null) {
            return;
        }
        mEventBus.post(mEventBus);
    }

    public void register(Object subscriber) {
        if (mEventBus == null || subscriber == null) {
            return;
        }
        try {
            mEventBus.register(subscriber);
        } catch (Exception e) {
            Log.i(e.getLocalizedMessage());
        } finally {

        }
    }
    public void unregister(Object subscriber) {
        if (mEventBus == null || subscriber == null) {
            return;
        }
        try {
            mEventBus.register(subscriber);
        } catch (Exception e) {
            Log.i(e.getLocalizedMessage());
        } finally {

        }
    }

    private EventManager() {
        //构建eventbus
        mEventBus = EventBus.getDefault();
    }

    private static class Inner {
        static private EventManager mInstance = new EventManager();
    }

    public static EventManager getInstance() {
        return Inner.mInstance;
    }
}
