package com.android.testmvp.maincontract;

import com.android.mvpdemo.base.BasePresenter;
import com.android.mvpdemo.base.BaseView;
import com.android.testmvp.network.NetApi;
import com.android.testmvp.network.RetrofitUtils;
import com.android.testmvp.network.RxHelper;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

public class TestContract {
    public static class TestPresenter extends BasePresenter<TestView> {
        public void getNet() {
            Observable<String> observable = RetrofitUtils.create(NetApi.class, NetApi.BASE_BAIDU_URL)
                    .get_baidu();
            observable.compose(RxHelper.<String>handleResult3()).subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {
                    getView().showLoading("开始访问百度");
                }

                @Override
                public void onNext(@NonNull String s) {
                    getView().showLoading(s);
                }

                @Override
                public void onError(@NonNull Throwable e) {
                    getView().showLoading(e.getMessage());
                    getView().hideLoading();
                }

                @Override
                public void onComplete() {
                    getView().hideLoading();
                }
            });
        }

    }

    public static interface TestView<T> extends BaseView {
        void showLoading(T data);

        void hideLoading();
    }
}
