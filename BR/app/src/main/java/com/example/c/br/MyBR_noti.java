package com.example.c.br;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by c on 2015-01-18.
 */
public class MyBR_noti extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification noti = new Notification(R.drawable.ic_launcher, "BR START!!!", System.currentTimeMillis());

        Intent myIntent = new Intent(context, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(context, 0, myIntent, 0);

        noti.setLatestEventInfo(context, "BR START TITLE", "BR START -CNTEXT TEXT", pIntent);
        manager.notify(1234, noti);
    }
}
