package com.example.mytest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mytest.databinding.ActivityMainBinding;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";
    private MyViewModel myViewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "B+onCreate");
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
//        binding.setVariable(BR.test, myViewModel);
        binding.setTest(myViewModel);
        binding.setLifecycleOwner(this);
        getLifecycle().addObserver(binding.meter);
        IniRefect();

    }

    private void IniRefect(){
        try {
            Class<?> clazz = Class.forName("com.example.mytest.Refect");
            Method getInstance = clazz.getMethod("getInstance");
            Object instance = getInstance.invoke(null);
            Method add = clazz.getDeclaredMethod("add", int.class, String.class);
            add.setAccessible(true);
            Object object = add.invoke(instance, 10, "莫等闲");
            if(object instanceof String){
                String ret = (String) object;
                Log.e(TAG, "反射字符串的返回值：: " + ret);
            }else if(object instanceof Integer){
                int ret = (int) object;
                Log.e(TAG, "反射整数的返回值：: " + ret);
            }
        }catch (Exception e){
            Log.e(TAG, "异常: " + e);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "B+onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "B+onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "B+onPause");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "B+onRestart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "B+onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "B+onDestroy......");
    }
}