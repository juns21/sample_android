package com.example.c.p01_musicplayer;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

public class MediaService extends Service {
    AudioPlayer mPlayer = new AudioPlayer();
    boolean mFlag;

    public MediaService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mFlag = true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPlayer.stop();
        mFlag = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //return super.onStartCommand(intent, flags, startId);
        final Context context = this.getApplicationContext();
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                if (mFlag) {
                    mPlayer.play(context);
                }
            }
        });

        th.start();
        return START_STICKY;
    }
}
