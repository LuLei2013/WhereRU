package com.whereru.greengrass.goforit.baidupush;

import android.content.Context;

import com.baidu.android.pushservice.PushConstants;
import com.whereru.greengrass.goforit.baidupush.entity.PushMessage;
import com.whereru.greengrass.goforit.baidupush.utils.PushPreferences;

/**
 * Created by lulei on 16/5/12.
 */
public class PushManager {
    public static void startPush(Context context) {
        com.baidu.android.pushservice.PushManager.startWork(context, PushConstants.LOGIN_TYPE_API_KEY, "uSCb0UhENlVZUC3DMUI02LH8FeaNl45H");
        registerGoIntoPushCallBack(context);
        registerGoOutPushCallBack(context);
        registerSwitchPushCallBack(context);
    }

    public static void stopPush(Context context) {
        com.baidu.android.pushservice.PushManager.stopWork(context);
        unregisterSwitchPushCallBack(context);
        unregisterGoOutPushCallBack(context);
        unregisterGoIntoPushCallBack(context);
    }

    private static void unregisterGoOutPushCallBack(final Context context) {
        UiHandler.unregisterPushMessageComingLisener(UiHandler.MSG_RECEIVE_PUSH_GOING_OUT_BUSINISS_MSG);
    }

    private static void unregisterSwitchPushCallBack(final Context context) {
        UiHandler.unregisterPushMessageComingLisener(UiHandler.MSG_RECEIVE_PUSH_SWITCH_BUSINISS_MSG);
    }

    private static void unregisterGoIntoPushCallBack(final Context context) {
        UiHandler.unregisterPushMessageComingLisener(UiHandler.MSG_RECEIVE_PUSH_GOING_INTO_BUSINISS_MSG);
    }


    private static void registerGoOutPushCallBack(final Context context) {
        UiHandler.registerPushMessageComingLisener(UiHandler.MSG_RECEIVE_PUSH_GOING_OUT_BUSINISS_MSG, new UiHandler.PushMessageComingLisener() {

            @Override
            public void onMessageComing(PushMessage msg) {
                //离开商圈时,清空本地缓存的商圈id,设置其为-1
                Log.e(" registerGoOutPushCallBack receive push business ID:" + msg.getData().getBusinessId());
                PushPreferences.getInstance(context).setCurrentBusinessId(-1l);
            }
        });
    }

    private static void registerSwitchPushCallBack(final Context context) {
        UiHandler.registerPushMessageComingLisener(UiHandler.MSG_RECEIVE_PUSH_SWITCH_BUSINISS_MSG, new UiHandler.PushMessageComingLisener() {

            @Override
            public void onMessageComing(PushMessage msg) {
                Log.e(" registerSwitchPushCallBack receive push business ID:" + msg.getData().getBusinessId());
                PushPreferences.getInstance(context).setCurrentBusinessId(msg.getData().getBusinessId());
            }
        });
    }

    private static void registerGoIntoPushCallBack(final Context context) {
        UiHandler.registerPushMessageComingLisener(UiHandler.MSG_RECEIVE_PUSH_GOING_INTO_BUSINISS_MSG, new UiHandler.PushMessageComingLisener() {

            @Override
            public void onMessageComing(PushMessage msg) {
                Log.e(" registerGoIntoPushCallBack receive push business ID ++++:" + msg.getData().getBusinessId());
                PushPreferences.getInstance(context).setCurrentBusinessId(msg.getData().getBusinessId());
                Log.e(" registerGoIntoPushCallBack receive push business ID ---:" + PushPreferences.getInstance(context).getCurrentBusinessId());
            }
        });
    }

}

