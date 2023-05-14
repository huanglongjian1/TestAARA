package com.android.testmvp;

import android.os.Bundle;
import android.widget.TextView;

import com.android.mvpdemo.mvp.MvpFragment;
import com.android.testmvp.maincontract.MainContract;
import com.android.testmvp.maincontract.TestContract;

import java.nio.charset.StandardCharsets;

public class TestFragment extends MvpFragment<TestContract.TestPresenter> implements TestContract.TestView<String> {
    TestActivity testActivity;

    @Override
    public void initData(Bundle savedInstanceState) {
        mPresenter.getNet();
        testActivity = (TestActivity) context;

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_test_fragment;
    }

    @Override
    protected TestContract.TestPresenter createPresent() {
        return new TestContract.TestPresenter();
    }

    @Override
    public void showLoading(String data) {
        TextView textView = rootView.findViewById(R.id.show_info_tv_test);
        textView.setText(data);
        Loge.e(data);
    }

    @Override
    public void hideLoading() {

    }
}
