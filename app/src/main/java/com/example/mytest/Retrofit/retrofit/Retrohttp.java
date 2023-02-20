package com.example.mytest.Retrofit.retrofit;

import android.util.Log;

import com.example.mytest.Retrofit.Interface.Tuinterface;
import com.example.mytest.Retrofit.bean.Tubean;
import com.google.gson.Gson;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrohttp {

    private static String TAG = "Retrohttp";
    Tuinterface tuinterface;

    public Retrohttp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://console-mock.apipost.cn")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        tuinterface = retrofit.create(Tuinterface.class);
    }

    public void getTubean(){
        Log.e(TAG, "步骤1");
//        tuinterface.getBean().enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                Log.e(TAG, "步骤3");
//                Log.e(TAG, "onNext: " + "id: " + response.body());
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                Log.e(TAG, "步骤4" + t);
//            }
//        });
        tuinterface.getBean()
                .map(new Function<Tubean, Object>() {
                    @Override
                    public Object apply(Tubean tubean) throws Throwable {
                        Log.e(TAG, "线程1: " + Thread.currentThread().getName());
                        return tubean;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        Log.e(TAG, "步骤2");
    }

    public Observer observer = new Observer<Tubean>() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {

        }

        @Override
        public void onNext(Tubean o) {
            Log.e(TAG, "线程2: " + Thread.currentThread().getName());
            Log.e(TAG, "步骤3");
            Log.e(TAG, "onNext: " + "id: " + o.id + " name: " + o.name + " " + new Gson().toJson(o));
        }

        @Override
        public void onError(@NonNull Throwable e) {
            Log.e(TAG, "步骤4: " + e);
        }

        @Override
        public void onComplete() {
//            Log.e(TAG, "取消订阅了哦。。。。。。");
        }
    };

}
