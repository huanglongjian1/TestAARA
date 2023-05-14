package com.android.testmvp.maincontract;

import android.annotation.SuppressLint;

import com.android.mvpdemo.base.BasePresenter;
import com.android.mvpdemo.base.BaseView;
import com.android.testmvp.network.NetApi;
import com.android.testmvp.network.RetrofitUtils;
import com.android.testmvp.network.RxHelper;
import com.android.testmvp.network.bean.image.telphone.InfoBean;
import com.android.testmvp.network.bean.image.telphone.PhoneAddress;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;


/**
 * 将V与M订阅起来
 *
 * @author llw
 */
public class MainContract {

    public static class MainPresenter extends BasePresenter<IMainView> {

        @SuppressLint("CheckResult")
        public void getWallPaper() {


        }
    }

    public interface IMainView<T> extends BaseView {
        void getWallPaper(T wallPaperResponse);

        //获取列表失败返回
        void getWallPaperFailed(Throwable e);
    }
}

