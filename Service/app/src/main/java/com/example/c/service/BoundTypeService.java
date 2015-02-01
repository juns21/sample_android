package com.example.c.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import java.util.Random;

public class BoundTypeService extends Service {
    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        BoundTypeService getService() {
            return BoundTypeService.this;
        }
    }

    public BoundTypeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return mBinder;
    }

    private final Random mGenerator = new Random();
    public int getRandomNumber() {
        return mGenerator.nextInt(100);
    }
}
