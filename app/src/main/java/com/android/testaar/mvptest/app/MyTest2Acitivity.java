package com.android.testaar.mvptest.app;

import android.os.Bundle;

import com.android.mvplibrary.MvpActivity;
import com.android.testaar.Loge;
import com.android.testaar.R;
import com.android.testaar.bean.WallPaperResponse;
import com.android.testaar.contract.MainContract;

public class MyTest2Acitivity extends MvpActivity<MainContract.MainPresenter> implements MainContract.IMainView {
    @Override
    protected MainContract.MainPresenter createPresenter() {
        return new MainContract.MainPresenter();

    }

    @Override
    public void initData(Bundle savedInstanceState) {
       mPresenter.getWallPaper();
    }

    @Override
    public int getLayoutId() {
        return R.layout.test1_layout;
    }

    @Override
    public void getWallPaper(WallPaperResponse wallPaperResponse) {
        Loge.e(wallPaperResponse.getRes().getVertical().size()+"-");
        Loge.e(wallPaperResponse.getRes().getVertical().get(0).getImg());
    }

    @Override
    public void getWallPaperFailed(Throwable e) {

    }
}
