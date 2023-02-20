package com.example.mytest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.security.Provider;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;

public class MyService extends Service{

    private static String TAG = "MyService";
    private Mybinder mybinder = new Mybinder();

    public class Mybinder extends Binder{
        public int number = 10;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "Service_onBind");
        return mybinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "Service_onCreate");
    }

}
