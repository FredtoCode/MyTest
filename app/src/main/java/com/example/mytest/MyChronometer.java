package com.example.mytest;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Chronometer;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;

public class MyChronometer extends Chronometer implements LifecycleEventObserver {

    private String TAG = "MyChronometer";
    private long elapseTimes;
    public MyChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void onStateChanged(@NonNull LifecycleOwner source, @NonNull Lifecycle.Event event) {
        switch (event){
            case ON_CREATE:
                start();
                Log.e(TAG, "ON_CREATE");
                break;
            case ON_START:
                Log.e(TAG, "ON_START");
                break;
            case ON_RESUME:
                setBase(SystemClock.elapsedRealtime() - elapseTimes);
                Log.e(TAG, "ON_RESUME");
                break;
            case ON_PAUSE:
                elapseTimes = SystemClock.elapsedRealtime() - getBase();
                Log.e(TAG, "ON_PAUSE");
                break;
            case ON_STOP:
                Log.e(TAG, "ON_STOP");
                break;
            case ON_DESTROY:
                Log.e(TAG, "ON_DESTROY");
                break;
        }
    }

}
