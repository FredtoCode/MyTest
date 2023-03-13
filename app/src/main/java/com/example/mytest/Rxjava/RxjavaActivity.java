package com.example.mytest.Rxjava;

import android.os.Bundle;
import android.util.Log;

import com.example.mytest.R;
import com.example.mytest.Retrofit.bean.Tubean;
import com.example.mytest.Retrofit.retrofit.Retrohttp;
import com.google.gson.Gson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class RxjavaActivity extends AppCompatActivity {

    private static String TAG = "RxjavaActivity";
    private Disposable disposable;
    private Observer observer = new Observer() {
        @Override
        public void onSubscribe(@NonNull Disposable d) {
            Log.e(TAG, "FFFFF: " + Thread.currentThread().getName());
            disposable = d;
        }

        @Override
        public void onNext(Object o) {
            Log.e(TAG, "data: " + o);
            Log.e(TAG, "DDDDD: " + Thread.currentThread().getName());
//            disposable.dispose();
        }

        @Override
        public void onError(@NonNull Throwable e) {

        }

        @Override
        public void onComplete() {
            Log.e(TAG, "取消订阅了哦。。。。。。");
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
//        Tubean tubean = new Tubean();
//        Log.e(TAG, "bean: " + new Gson().toJson(tubean));

        Retrohttp retrohttp = new Retrohttp();
        retrohttp.getTubean();

        getint getint = new getint("null", 1);
        Log.e(TAG, "NoNull: ");

        RxBus.get().post("rxbus_9000");
        RxBus.get().post("rxbus_8000");

        RxBus.get().post(8080);

        RxBus.get().toObservable(String.class)
                .subscribe(observer);
//                .subscribe(new Observer<String>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.e(TAG, "RxBus: " + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull String s) {
//                        Log.e(TAG, "RxBus_String: " + s);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });

        RxBus.get().toObservable(Integer.class)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.e(TAG, "RxBus: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(@NonNull Integer s) {
                        Log.e(TAG, "RxBus_Int: " + s);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        ArrayList<String> int_list = new ArrayList<>(5);
//        int_list.add("1001");
//        int_list.add("1002");
//        int_list.add("1003");
//        Observable.create(new ObservableOnSubscribe<ArrayList>() {
//                    @Override
//                    public void subscribe(@NonNull ObservableEmitter<ArrayList> emitter) throws Throwable {
//                        emitter.onNext(int_list);
//                        Log.e(TAG, "AAAAA: " + Thread.currentThread().getName());
//                        emitter.onComplete();
//                    }
//                }).subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.io())
//                .flatMap(new Function<ArrayList, ObservableSource<String>>() {
//                    @Override
//                    public ObservableSource<String> apply(ArrayList arrayList) throws Throwable {
//                        Log.e(TAG, "EEEEE: " + Thread.currentThread().getName());
//                        Log.e(TAG, "EEEEE: " + arrayList);
//                        return Observable.fromIterable(arrayList);
//                    }
//                })
//                .map(new Function<String, String>() {
//                    @Override
//                    public String apply(String oneOFarray) throws Throwable {
//                        Log.e(TAG, "BBBBB: " + Thread.currentThread().getName());
//                        return oneOFarray;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(new Function<String, ObservableSource<Integer>>() {
//                    @Override
//                    public ObservableSource<Integer> apply(String s) throws Throwable {
//                        Log.e(TAG, "CCCCC: " + Thread.currentThread().getName());
//                        return Observable.just(Integer.parseInt(s));
//                    }
//                })
//                .observeOn(Schedulers.io())
////                .subscribe(observer);
//                .subscribe(new Observer<Integer>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        Log.e(TAG, "GGGGG: " + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onNext(@NonNull Integer o) {
//                        Log.e(TAG, "data: " + o);
//                        Log.e(TAG, "DDDDD: " + Thread.currentThread().getName());
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });


    }

    class getint{
        String s;
        public <T> getint(@NonNull String s, T a){
            this.s = s;
        }
    }
}
