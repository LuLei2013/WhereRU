package com.whereru.greengrass.goforit.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import com.whereru.greengrass.goforit.R;
import com.whereru.greengrass.goforit.activity.MainActivity;
import com.whereru.greengrass.goforit.baidupush.entity.PushMessage;

/**
 * Created by didi on 16/5/20.
 */
public class NotificationMana {

    public static void addNotificaction(Context context, PushMessage message) {
        String ticket = "您已近进入商铺";
        if (message.getStatus() == 2) {
            ticket = "您已离开商铺";
        }
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
                new Intent(context, MainActivity.class), 0);
        Notification notification = new Notification.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(ticket)
                .setContentTitle(message.getData().getName())
                .setContentText(message.getData().getAbstract())
                .setContentIntent(pendingIntent)
                .setNumber(1)
                .getNotification();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        manager.notify(R.drawable.notification_icon, notification);
    }
}
