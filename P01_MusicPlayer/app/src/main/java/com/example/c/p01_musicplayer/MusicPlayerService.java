package com.example.c.p01_musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

public class MusicPlayerService extends Service {
    private MediaPlayer mPlayer = null;
    private final IBinder mBinder = new LocalBinder();
    MediaPlayerSQLiteHandler mDBHandler;
    public class LocalBinder extends Binder {
        MusicPlayerService getService(){
            return MusicPlayerService.this;
        }
    }
    public MusicPlayerService() {
        mDBHandler = new MediaPlayerSQLiteHandler(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return mBinder;
    }

    public void play(){
        String path = Environment.getExternalStorageDirectory().toString();
        path += "/Samsung/Music/Over_the_horizon.mp3";

        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(path);
            mPlayer.prepare();
        }catch (Exception e){
            e.printStackTrace();
        }

        mPlayer.start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mPlayer.isPlaying()) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    mDBHandler.update(1, mPlayer.getCurrentPosition());
                }
            }
        }).start();
    }

    public void stop(){
        if(mPlayer != null){
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = null;
    }
}
