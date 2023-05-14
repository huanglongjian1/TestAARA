package com.android.testmvp;


import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.mvpdemo.base.BasePresenter;
import com.android.mvpdemo.mvp.MvpActivity;
import com.android.testmvp.bean.User;
import com.android.testmvp.dagger2.DaggerApplicationComponent;
import com.android.testmvp.dagger2.NetModule;
import com.android.testmvp.maincontract.MainContract;
import com.android.testmvp.util.Constants;

import javax.inject.Inject;

@Route(path = Constants.TEST_SCHEME_ACTIVITY)
public class TestSchemeActivity extends MvpActivity implements MainContract.IMainView {

    @Inject
    User hlj;

    @Override
    public void initData(Bundle savedInstanceState) {
        // DaggerApplicationComponent.create().inject(this);
        // DaggerApplicationComponent.builder().build().inject(this);
        DaggerApplicationComponent.builder().netModule(new NetModule()).build().inject(this);
        TextView textView = findViewById(R.id.tv_scheme);
        textView.setText(hlj.toString());
    }

    @Override
    public int getLayoutId() {
        return R.layout.settings_activity;
    }

    @Override
    protected BasePresenter createPresenter() {
        return new MainContract.MainPresenter();
    }

    @Override
    public void getWallPaper(Object wallPaperResponse) {

    }

    @Override
    public void getWallPaperFailed(Throwable e) {

    }
}
