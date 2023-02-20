package com.example.mytest;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.util.Printer;
import android.widget.ImageView;

import com.example.mytest.BroadcastReceiver.FirstBroadcastReceiver;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainTestActivity extends AppCompatActivity {

    private static String TAG = "MainTestActivity";
    private Intent intent;
    private MyService.Mybinder binder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytest);
        Log.e(TAG, "A+onCreate");
        intent = new Intent(this, MyService.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "A+onStart");
        getApplicationContext().bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "A+onResume");
//        getApplicationContext().bindService(intent, serviceConnection, Service.BIND_AUTO_CREATE);
        Intent intent = new Intent();
        intent.setAction("com.intent.action.TEST");
        intent.setPackage("com.example.mytest");
//        Intent intent = new Intent(this, FirstBroadcastReceiver.class);
        intent.putExtra("msg", "这里这里");
        sendOrderedBroadcast(intent, null);
        Printer printer = new Printer() {
            @Override
            public void println(String x) {

            }
        };
        Looper.getMainLooper().setMessageLogging(printer);
        Log.e(TAG, "A+onResume_end");
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.e(TAG, "onServiceConnected");
            binder = (MyService.Mybinder) service;
            Log.e(TAG, "onServiceConnected: " + binder.number);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "A+onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "A+onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int a = 0;
//                while (a < 10){
//                    a += 1;
//                    try {
//                        Thread.sleep(2000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Log.e(TAG, "A+onResume" + " a: " + a);
//                }
//                Log.e(TAG, "onDestroy" + ": Thread_start");
////                onDestroy();
//                Log.e(TAG, "onDestroy" + ": Thread_end");
//            }
//        }).start();
        Log.e(TAG, "A+onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "A+onDestroy");
    }
}
