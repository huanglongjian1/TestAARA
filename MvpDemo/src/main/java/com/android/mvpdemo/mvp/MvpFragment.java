package com.android.mvpdemo.mvp;


import android.os.Bundle;

import com.android.mvpdemo.base.BaseFragment;
import com.android.mvpdemo.base.BasePresenter;
import com.android.mvpdemo.base.BaseView;


/**
 * 适用于需要访问网络接口的Fragment
 *
 * @author llw
 */
public abstract class MvpFragment<P extends BasePresenter> extends BaseFragment {

    protected P mPresenter;

    /**
     * 创建Presenter
     */
    protected abstract P createPresent();

    @Override
    public void initBeforeView(Bundle savedInstanceState) {
        mPresenter = createPresent();
        mPresenter.attachView((BaseView) this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}

