package com.example.mytest.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SecondBroadcastReceiver extends BroadcastReceiver {
    private static String TAG = "SecondBroadcastReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "intent信息: " + intent.getStringExtra("msg"));
        Bundle bundle = getResultExtras(true);
        Log.e(TAG, "bundle信息: " + bundle.getString("first"));
    }
}
