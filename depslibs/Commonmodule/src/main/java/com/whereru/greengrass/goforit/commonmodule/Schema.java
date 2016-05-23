package com.whereru.greengrass.goforit.commonmodule;

/**
 * Created by didi on 16/5/23.
 */
public class Schema {
    /**
     * Event分发时的schema,统一入口,注册在MainActivity中
     */
    public static final String EVENT_SCHEMA = "whereru";
    /**
     * Event分发时的schema,分发到具体Activity
     */
    public static final String EVENT_ACTION = "android.intent.action.VIEW";

}
