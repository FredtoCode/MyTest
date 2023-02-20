package com.example.mytest.Rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import io.reactivex.rxjava3.subjects.Subject;

public class RxBus {
    private final Subject<Object> mBus;
    private static Disposable disposable;
    private static class Holder{
        private static final RxBus BUS = new RxBus();
    }

    private RxBus(){
        // 除了onSubscribe,onNext,onError,onComPlete
        // 支持线程安全toSerialize
        // 只接收最后一个 AsyncSubject
        // 订阅前执行最后一个 订阅后全部执行 BehaviorSubject
        // 接收全部数据 ReplaySubject
        // 只接收订阅后的所有数据 PublishSubject
        mBus = ReplaySubject.create().toSerialized();
    }

    public static RxBus get(){
        return Holder.BUS;
    }

    public void post(Object event){
        mBus.onNext(event);
    }

    public @NonNull <T> Observable<T> toObservable(Class<T> tClass){
        return mBus.ofType(tClass);
    }

 }
