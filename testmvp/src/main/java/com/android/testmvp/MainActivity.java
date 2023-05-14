package com.android.testmvp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityOptionsCompat;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.mvpdemo.mvp.MvpActivity;
import com.android.testmvp.app.MyApp;
import com.android.testmvp.dagger2.DaggerApplicationComponent;
import com.android.testmvp.dagger2.TestOneIInterceptor;
import com.android.testmvp.maincontract.MainContract;
import com.android.testmvp.network.NetApi;
import com.android.testmvp.network.RxHelper;
import com.android.testmvp.network.bean.image.telphone.InfoBean;
import com.android.testmvp.network.bean.image.telphone.PhoneAddress;
import com.android.testmvp.util.Constants;
import com.tbruyelle.rxpermissions3.RxPermissions;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import javax.inject.Inject;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;

@Route(path = Constants.MAIN_ACTIVITY, extras = 1)
public class MainActivity extends MvpActivity<MainContract.MainPresenter> implements MainContract.IMainView<InfoBean> {

    @Inject
    NetApi netApi;
    TextView textView;

    @Override
    public void initData(Bundle savedInstanceState) {
        DaggerApplicationComponent.create().inject(this);
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean aBoolean) throws Throwable {
                        Log.e("权限", "获取成功");
                    }
                });


        netApi.getPhone("15577496854").compose(RxHelper.<PhoneAddress, InfoBean>handleResult2())
                .subscribe(new Observer<InfoBean>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull InfoBean infoBean) {
                        Loge.e(infoBean.toString());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        textView = findViewById(R.id.tv_mvp);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptionsCompat compat = ActivityOptionsCompat.
                        makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
                Uri uri = Uri.parse(Constants.TEST5_ACTIVITY);
                ARouter.getInstance().build(uri)
                        .withOptionsCompat(compat)
                        .navigation();
                // toActivity(MainActivity.this, Constants.TEST_SCHEME_ACTIVITY);

            }


        });
        new TestOneIInterceptor().setcallback(new TestOneIInterceptor.Callback() {
            @Override
            public void call() {
                //    ARouter.getInstance().build(Constants.TEST_SCHEME_ACTIVITY).navigation();
            }
        });


        mPresenter.getWallPaper();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!TextUtils.isEmpty(getIntent().getStringExtra("key")))
            textView.setText(getIntent().getStringExtra("key"));
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.MainPresenter createPresenter() {
        return new MainContract.MainPresenter();
    }

    @Override
    public void getWallPaper(InfoBean wallPaperResponse) {

        Loge.e(wallPaperResponse.toString());


    }


    @Override
    public void getWallPaperFailed(Throwable e) {
        TextView textView = findViewById(R.id.tv_mvp);
        textView.setText(e.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ARouter.getInstance().destroy();
    }
}