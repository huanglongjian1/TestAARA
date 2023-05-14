package com.android.my_mvplibrary.test.mvp;

import android.util.Log;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.GET;

public class MainModelImpl implements MainModel {
    interface GitHubService {

        @GET("/")
        Observable<String> getUser();

    }

    @Override
    public Observable getDataFromNetString() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.baidu.com")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        GitHubService gitHubService = retrofit.create(GitHubService.class);

        return gitHubService.getUser();
    }

    @Override
    public String getDataFromNet() {
        return "MVP 模式,into fragment";
    }

    @Override
    public void stopRequest() {
        Log.i("MainModelImpl", "stop request...");
    }
}
