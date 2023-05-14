package com.android.testmvp.util;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.mvpdemo.base.BasePresenter;
import com.android.mvpdemo.mvp.MvpActivity;
import com.android.testmvp.MyFragment;
import com.android.testmvp.R;
import com.android.testmvp.dagger2.IFragmentService;
import com.android.testmvp.maincontract.MainContract;
import com.android.testmvp.network.bean.image.telphone.PhoneAddress;

@Route(path = Constants.OTHER_ACTIVITY)
public class OtherActivity extends MvpActivity<MainContract.MainPresenter> implements MainContract.IMainView<String> {

    @Override
    public void initData(Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        Fragment fragment = ((IFragmentService) ARouter.getInstance().build(Constants.MY_FRAGMENT).navigation()).createFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_my, fragment).commit();

    }

    @Override
    public int getLayoutId() {
        return R.layout.other_layout;
    }

    @Override
    protected MainContract.MainPresenter createPresenter() {
        return new MainContract.MainPresenter();
    }

    @Override
    public void getWallPaper(String wallPaperResponse) {

    }

    @Override
    public void getWallPaperFailed(Throwable e) {

    }
}
