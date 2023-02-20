package com.example.mytest.Retrofit.Interface;

import com.example.mytest.Retrofit.bean.Tubean;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Tuinterface {

    @GET("/app/mock/project/a717252c-9043-40ce-94e4-9e12259b2628/001")
    Observable<Tubean> getBean();
}
