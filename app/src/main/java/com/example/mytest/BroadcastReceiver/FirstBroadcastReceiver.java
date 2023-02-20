package com.example.mytest.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class FirstBroadcastReceiver extends BroadcastReceiver {
    private static String TAG = "FirstBroadcasrReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "intent信息：" + intent.getStringExtra("msg"));
        Bundle bundle = new Bundle();
        bundle.putString("first", "第一个广播信息");
        setResultExtras(bundle);
        abortBroadcast();
    }
}
