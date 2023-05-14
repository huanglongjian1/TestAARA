package com.android.testmvp;

import android.os.Bundle;

import com.android.mvpdemo.mvp.MvpFragment;
import com.android.testmvp.maincontract.Test2Contract;

public class Test3Fragment extends MvpFragment<Test2Contract.Test2Presenter> implements Test2Contract.Test2View {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.bottom_navigation;
    }

    @Override
    protected Test2Contract.Test2Presenter createPresent() {
        return new Test2Contract.Test2Presenter();
    }
}
