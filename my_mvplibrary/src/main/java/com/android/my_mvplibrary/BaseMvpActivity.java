package com.android.my_mvplibrary;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.my_mvplibrary.presenter.Presenter;
import com.android.my_mvplibrary.test.mvp.MainModel;
import com.android.my_mvplibrary.view.View;

public abstract class BaseMvpActivity<M extends MainModel,V extends View,P extends Presenter> extends AppCompatActivity implements BaseMvp<M,V,P> {
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //创建Presenter
        presenter =  createPresenter();
        if (presenter != null) {
//将Model层注册到Presenter中
            presenter.registerModel(createModel());
//将View层注册到Presenter中
            presenter.registerView(createView());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
//Activity销毁时的调用，让具体实现BasePresenter中onViewDestroy()方法做出决定
            presenter.destroy();
        }
    }
}