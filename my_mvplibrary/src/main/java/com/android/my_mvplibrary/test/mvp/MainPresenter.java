package com.android.my_mvplibrary.test.mvp;

import com.android.my_mvplibrary.presenter.BasePresenter;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;

public class MainPresenter extends BasePresenter {
    public void getData() {//这里要注意判空(view和model可能为空)
        String dataFromNet = null;
        if ((MainModelImpl) model != null) {
            dataFromNet = ((MainModelImpl) model).getDataFromNet();
        }
        if (getView() != null) {
            ((MainView) getView()).setData(dataFromNet);
        }
    }

    public void getDataFromNet() {
        if (model != null) {
            Observable<String> observable = ((MainModelImpl) model).getDataFromNetString();
            observable.subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    ((MainView) getView()).setData(s);
                }
            });
        }
    }

    @Override
    protected void onViewDestroy() {//销毁Activity时的操作，可以停止当前的model
        if (model != null) {
            ((MainModelImpl) model).stopRequest();
        }
    }
}
