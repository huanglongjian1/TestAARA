package com.android.testmvp.network;

import android.app.Dialog;

import com.android.mvpdemo.BaseApplication;
import com.android.testmvp.Loge;
import com.android.testmvp.network.bean.Bean;
import com.android.testmvp.network.bean.ImagesBean;
import com.android.testmvp.network.bean.image.telphone.PhoneAddress;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.ObservableTransformer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class RxHelper {
    //去壳处理
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return new ObservableTransformer<BaseResponse<T>, T>() {
            @Override
            public ObservableSource<T> apply(Observable<BaseResponse<T>> upstream) {
                return (ObservableSource<T>) upstream.flatMap(new Function<BaseResponse<T>, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(BaseResponse<T> tBaseResponse) throws Exception {
                                int code = tBaseResponse.getStatus();
                                String desc = tBaseResponse.getMessage();
                                T data = tBaseResponse.getObj();
                                //Log.e("CTB_RxHelper: ", data.toString());
                                if (code == RetrofitUtils.NET_SUCCESS_CODE) {
                                    return createObservable(data);
                                } else {
                                    return Observable.error(new ServerException(code, desc));
                                }
                            }
                        }).subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    //去壳处理
    public static <K, T> ObservableTransformer<K, T> handleResult2() {
        return new ObservableTransformer<K, T>() {
            @Override
            public ObservableSource<T> apply(Observable<K> upstream) {
                return (ObservableSource<T>) upstream
                        .flatMap(new Function<K, ObservableSource<?>>() {
                            @Override
                            public ObservableSource<?> apply(K bean) throws Throwable {
                                T info = (T) ((PhoneAddress) bean).getInfo();
                                return Observable.just(info);
                            }
                        })
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());

            }
        };
    }

    public static<T> ObservableTransformer<T,T> handleResult3() {

        return new ObservableTransformer<T, T>() {
            @Override
            public @NonNull ObservableSource<T> apply(@NonNull Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    // 创建指定数据源的Observable
    // 即：将后端传来的数据，如果为空，则则直接走 onComplete(); 否则，走onNext()，即传数据
    private static <T> Observable<T> createObservable(final T t) {
        return Observable.create(new ObservableOnSubscribe<T>() {
            @Override
            public void subscribe(ObservableEmitter<T> e) {
                if (e != null) {
                    if (t != null) {
                        e.onNext(t);        // 到observe的onNext()
                    }
                    e.onComplete();
                }
            }
        });
    }
}

