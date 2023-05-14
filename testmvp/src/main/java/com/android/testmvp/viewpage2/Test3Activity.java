package com.android.testmvp.viewpage2;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.mvpdemo.mvp.MvpActivity;
import com.android.testmvp.R;
import com.android.testmvp.maincontract.Test2Contract;
import com.android.testmvp.maincontract.TestContract;
import com.android.testmvp.util.Constants;

@Route(path = Constants.TEST3_ACTIVITY)
public class Test3Activity extends MvpActivity<Test2Contract.Test2Presenter> implements TestContract.TestView {
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public int getLayoutId() {
        return R.layout.bottom_navigation;
    }

    @Override
    protected Test2Contract.Test2Presenter createPresenter() {
        return new Test2Contract.Test2Presenter();
    }

    @Override
    public void showLoading(Object data) {

    }

    @Override
    public void hideLoading() {

    }
}
