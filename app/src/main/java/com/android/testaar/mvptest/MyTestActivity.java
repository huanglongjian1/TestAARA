package com.android.testaar.mvptest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.mvplibrary.MvpActivity;
import com.android.my_mvplibrary.test.TestActivity;
import com.android.testaar.Loge;
import com.android.testaar.R;
import com.android.testaar.bean.WallPaperResponse;
import com.android.testaar.contract.MainContract;
import com.android.testaar.t.Demo;

public class MyTestActivity extends MvpActivity<MainContract.MainPresenter> implements MainContract.IMainView{
    @Override
    protected MainContract.MainPresenter createPresenter() {
        return new MainContract.MainPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getWallPaper();
        findViewById(R.id.mvptest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Demo().new Child("child");
                startActivity(new Intent(MyTestActivity.this, TestActivity.class));
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.test_layout;
    }

    @Override
    public void getWallPaper(WallPaperResponse wallPaperResponse) {
        Loge.e(wallPaperResponse.getRes().getVertical().size() + "-");
    }

    @Override
    public void getWallPaperFailed(Throwable e) {

    }

}
