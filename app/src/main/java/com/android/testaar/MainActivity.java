package com.android.testaar;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.testaar.download.DownLoadHelper;
import com.android.testaar.download.FileUtil;
import com.igexin.sdk.PushManager;
import com.tbruyelle.rxpermissions3.RxPermissions;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import io.reactivex.rxjava3.functions.Consumer;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private static final int REQUEST_CODE = 3999;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testaar_activity_main);
        EventBus.getDefault().register(this);
        findViewById(R.id.aar_save_test2).setOnClickListener(this);
        textView = findViewById(R.id.aar_tv_test);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

        //    start_service();

        findViewById(R.id.aar_tv_test2).setOnClickListener(new View.OnClickListener() {
            int i = 0;

            @Override
            public void onClick(View v) {
                Log.e("onclick", "onclick");
                String cid = PushManager.getInstance().getClientid(getApplicationContext());

                Log.e("cid", cid);
                textView.setText(cid);
                textView.setTextIsSelectable(true);
                //   sendBroadcast(new Intent("android.intent.action.DOWNLOAD_COMPLETE"));

            }
        });

        DownLoadHelper.DownLoadCompleteReceiver downLoadCompleteReceiver = new DownLoadHelper.DownLoadCompleteReceiver();
        registerReceiver(downLoadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    final RxPermissions rxPermissions = new RxPermissions(this);

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aar_save_test2:
                rxPermissions.request(Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_NETWORK_STATE

                        )
                        .subscribe(new Consumer<Boolean>() {
                            @Override
                            public void accept(Boolean aBoolean) throws Exception {
                                if (aBoolean) {
                                    //授予所有权限
                                    Toast.makeText(MainActivity.this, "已授予权限", Toast.LENGTH_SHORT).show();

                                    Loge.e("下载进行中");
                                    DownLoadHelper.downLoadApk(MainActivity.this);

                                } else {
                                    //至少有一个权限未授予
                                    Toast.makeText(MainActivity.this, "未授予权限", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                break;
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getString(String s) {
        textView.setText(s);
    }
}