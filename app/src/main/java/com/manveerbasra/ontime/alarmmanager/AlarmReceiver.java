package com.manveerbasra.ontime.alarmmanager;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.manveerbasra.ontime.R;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class AlarmReceiver extends BroadcastReceiver {

    private final String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "received alarm intent");

        ComponentName comp = new ComponentName(context.getPackageName(),
                AlarmNotificationService.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));

//        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//
//        Notification notification = new NotificationCompat.Builder(context, CHANNEL_ID)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setPriority(NotificationCompat.PRIORITY_MAX)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("Alarm")
//                .setContentText("Upcoming Alarm.")
//                .build();
//
//        Log.i(TAG, "displaying notification");
//        notificationManager.notify(1, notification);

        // TODO: Set Activity that opens when Alarm goes off
        //
        // Notification notification = new NotificationCompat.Builder(context, "M_CH_ID")
        //        .setFullScreenIntent(PendingIntent intent, boolean highPriority=true)
        //        .build();

    }
}