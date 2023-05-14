package com.android.testmvp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.mvpdemo.base.BasePresenter;
import com.android.mvpdemo.base.BaseView;
import com.android.mvpdemo.mvp.MvpActivity;
import com.android.mvpdemo.mvp.MvpFragment;
import com.android.testmvp.maincontract.TestContract;
import com.android.testmvp.util.Constants;
import com.android.testmvp.util.FragmentUtils;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

@Route(path = Constants.TEST_ACTIVITY)
public class TestActivity extends MvpActivity<TestContract.TestPresenter> implements TestContract.TestView {


    @Override
    public void initData(Bundle savedInstanceState) {
        Button button = findViewById(R.id.btn_001);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.replace(new TestFragment(), TestActivity.this, R.id.fragment_test);
            }
        });
        Button button2 = findViewById(R.id.btn_002);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentUtils.replace(new Test2Fragment(), TestActivity.this, R.id.fragment_test);
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }


    @Override
    public void showLoading(Object data) {
        showLoadingDialog();
    }

    @Override
    public void hideLoading() {
        hideLoadingDialog();
    }

    @Override
    protected TestContract.TestPresenter createPresenter() {
        return new TestContract.TestPresenter();
    }
}