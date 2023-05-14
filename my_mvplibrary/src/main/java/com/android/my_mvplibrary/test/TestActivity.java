package com.android.my_mvplibrary.test;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.my_mvplibrary.BaseMvpActivity;
import com.android.my_mvplibrary.test.mvp.MainModel;
import com.android.my_mvplibrary.test.mvp.MainModelImpl;
import com.android.my_mvplibrary.test.mvp.MainPresenter;
import com.android.my_mvplibrary.test.mvp.MainView;

public class TestActivity extends BaseMvpActivity<MainModel,MainView,MainPresenter> implements MainView {

    @Override
    public MainModelImpl createModel() {
        return new MainModelImpl();
    }

    @Override
    public MainView createView() {
        return this;
    }

    @Override
    public MainPresenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
       presenter.getDataFromNet();
       presenter.getData();
    }

    @Override
    public void setData(String str) {
        Log.e("setData", str);
    }

    @Override
    public void showToast(String info) {

    }

    @Override
    public void showProgress() {

    }
}
