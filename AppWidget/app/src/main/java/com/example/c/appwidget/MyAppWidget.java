package com.example.c.appwidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by c on 2015-01-24.
 */
public class MyAppWidget extends AppWidgetProvider {
    final String ACTION_MYAPPWIDGET_DISPLAY = "MyAppwidget.Display";
    String[] weekDay = {"일", "월", "화", "수", "목", "금", "토"};

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        String action = intent.getAction();
        if (action.equals(ACTION_MYAPPWIDGET_DISPLAY)) {
            GregorianCalendar cal = new GregorianCalendar();

            String strWeek = weekDay[cal.get(Calendar.DAY_OF_WEEK) -1];
            String strDate = ""+ cal.get(Calendar.MONTH) +"월 "+ cal.get(Calendar.DAY_OF_MONTH)+"일";

            //Toast.makeText(context, strDate+", "+strWeek, Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        super.onUpdate(context, appWidgetManager, appWidgetIds);

        for (int i=0; i<appWidgetIds.length; i++) {
            GregorianCalendar cal = new GregorianCalendar();

            String strWeek = weekDay[cal.get(Calendar.DAY_OF_WEEK) -1];
            String strDate = ""+ cal.get(Calendar.MONTH) +"월 "+ cal.get(Calendar.DAY_OF_MONTH)+"일";

            RemoteViews remote = new RemoteViews(context.getPackageName(), R.layout.myappwidget);
            remote.setTextViewText(R.id.textViewDate, strDate);
            remote.setTextViewText(R.id.textViewWeek, strWeek);

            Intent intent = new Intent(context, MyAppWidget.class);
            intent.setAction(ACTION_MYAPPWIDGET_DISPLAY);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetIds[i]);

            PendingIntent pIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
            remote.setOnClickPendingIntent(R.id.leftButton, pIntent);

            appWidgetManager.updateAppWidget(appWidgetIds[i], remote);
        }
    }
}
