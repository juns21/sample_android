package com.example.c.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.Date;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");

        return null;
    }

    boolean mFlag;

    @Override
    public void onCreate() {
        super.onCreate();
        mFlag = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mFlag = false;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (mFlag) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Date d = new Date();
                    Log.i("MyService", d.toString());
                }
            }
        });

        th.start();
        return START_STICKY;
    }
}
