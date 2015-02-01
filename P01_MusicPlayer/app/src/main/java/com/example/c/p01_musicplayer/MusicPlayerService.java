package com.example.c.p01_musicplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;

import java.io.IOException;

/**
 * Created by c on 2015-02-01.
 */
public class MusicPlayerService extends Service {
    public class LocalBinder2 extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

    private LocalBinder2 mLocalBinder2 = new LocalBinder2();
    private MediaPlayer mPlayer = null;

    @Override
    public IBinder onBind(Intent intent) {
        return mLocalBinder2;
    }

    public void play() {
        String path = Environment.getExternalStorageDirectory().toString();
        path +="/Samsung/Music/Over_the_horizon.mp3";

        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(path);
            mPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mPlayer.start();
    }

    public void stop() {
        if (mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
            mPlayer = null;
        }
    }
}
