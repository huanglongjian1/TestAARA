package com.android.testmvp.app;

import android.os.Bundle;

import com.android.mvpdemo.base.IUiCallback;

public class TestInterface implements IUiCallback {
    @Override
    public void initBeforeView(Bundle savedInstanceState) {

    }


    public void initData(Bundle savedInstanceState) {


    }

    @Override
    public int getLayoutId() {
        return 0;
    }
}
