package com.whereru.greengrass.goforit.notification;

import java.lang.reflect.Method;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Build;

import com.whereru.greengrass.goforit.BaseApplication;

/**
 * created by lulei on 16/5/22
 * 通知栏UI
 */
public class Notification {


    private android.app.Notification createNotification(int resId) {
        String content = BaseApplication.sApplicatonContext.getString(resId);
        return createNotification(content);
    }

    private android.app.Notification createNotification(String content) {
//        Context c = BaseApplication.sApplicatonContext;
//
//        Intent i = new Intent(Intent.ACTION_MAIN);
//        i.addCategory(Intent.CATEGORY_LAUNCHER);
//        i.setClass(c, MainActivity.class);
//        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
//
//        PendingIntent intent = PendingIntent
//                .getActivity(c, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(c);
//        builder.setContentIntent(intent);
//        String title = c.getResources().getString(R.string.push_notification_title);
//
//        builder.setContentTitle(title);
//        if (!TextUtils.isEmpty(content)) {
//            builder.setTicker(content);
//            builder.setContentText(content);
//        }
//        builder.setSmallIcon(R.drawable.ic_notification);
//        builder.setAutoCancel(false);
//        builder.setOngoing(true);
//        builder.setWhen(TimeUtil.currentTimeMillis());
//
//        // 设置按钮点击事件，这里要放一个PendingIntent，注意只有在在sdk3.0以上的系统中，
//        // 通知栏中的按钮点击事件才能响应，这里最好加个条件，sdk3.0以下，不显示按钮
//        if (android.os.Build.VERSION.SDK_INT >= 11) {
//            RemoteViews contentView = null;
//            if (DeviceUtil.isMIPhone()) {
//                contentView = new RemoteViews(c.getPackageName(),
//                        R.layout.layout_notification_xiaomi);
//            } else {
//                contentView = new RemoteViews(c.getPackageName(), R.layout.layout_notification);
//            }
//
//            contentView.setTextViewText(R.id.txt_title, title);
//            if (!TextUtil.isEmpty(content)) {
//                contentView.setTextViewText(R.id.txt_content, content);
//            }
//            PendingIntent pi = hotelineIntent();
//            contentView.setOnClickPendingIntent(R.id.btn_hotline, pi);
//            contentView.setOnClickPendingIntent(R.id.layout_hotline, pi);
//
//            contentView.setViewVisibility(R.id.btn_hotline, View.VISIBLE);
//            builder.setContent(contentView);
//        }
//
//        android.app.Notification notification = builder.build();
//        notification.flags = android.app.Notification.FLAG_ONGOING_EVENT;
//        notification.flags |= android.app.Notification.FLAG_NO_CLEAR;
//        notification.flags |= android.app.Notification.FLAG_FOREGROUND_SERVICE;
//
//        IntentFilter filter = new IntentFilter(ACTION_CALL_HOTLINE_FROM_NOTIFICATON);
//        c.registerReceiver(receiver, filter);

//        return notification;
        return null;
    }


    /**
     * 增加点击事件统计，修改为broadcast，在广播接收中处理，并统计次数
     */
    private PendingIntent hotelineIntent() {
//        Intent intent = new Intent(ACTION_CALL_HOTLINE_FROM_NOTIFICATON);
//        PendingIntent pi = PendingIntent.getBroadcast(BaseApplication.getAppContext(), 0, intent,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        return pi;
        return null;
    }

    /**
     * Create and show a notification with a custom layout. This callback is defined through the
     * 'onClick' attribute of the 'Show Notification' button in the XML layout.
     */
    public android.app.Notification getNotification(int resId) {
        return createNotification(resId);
    }

    public android.app.Notification getNotification(String content) {
        return createNotification(content);
    }

    /**
     * 关闭系统通知栏
     *
     * @param context
     */
    public static void collapseStatusBar(Context context) {
        try {
            Object statusBarManager = context.getSystemService("statusbar");
            Method collapse;
            if (Build.VERSION.SDK_INT <= 16) {
                collapse = statusBarManager.getClass().getMethod("collapse");
            } else {
                collapse = statusBarManager.getClass().getMethod("collapsePanels");
            }
            collapse.invoke(statusBarManager);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
    }
}