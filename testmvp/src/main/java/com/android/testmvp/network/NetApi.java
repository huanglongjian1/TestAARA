package com.android.testmvp.network;

import com.android.testmvp.network.bean.Bean;
import com.android.testmvp.network.bean.image.ImageBean;
import com.android.testmvp.network.bean.image.telphone.PhoneAddress;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetApi {
    public static String BASE_URL = "https://cn.bing.com/HPImageArchive.aspx/";

    @GET("?format=js&idx=0&n=1")
    Observable<Bean> get();

    public static String BASE_URL_IMAGE = "https://api.vvhan.com/api/mobil.girl?type=json/";

    @GET("/")
    Observable<ImageBean> getImage();

    public static String BASE_URL_PHONE = "https://api.vvhan.com/api/";

    @GET("phone")
    Observable<PhoneAddress> getPhone(@Query("tel") String tel);


    public static String BASE_BAIDU_URL = "http://www.baidu.com/";

    @GET("/")
    Observable<String> get_baidu();
}
