package com.android.testaar;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.igexin.sdk.IUserLoggerInterface;
import com.igexin.sdk.PushManager;

public class MyAPP extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        PushManager.getInstance().initialize(this);
        com.igexin.sdk.PushManager.getInstance().setDebugLogger(this, new IUserLoggerInterface() {
            @Override
            public void log(String s) {
                Log.i("PUSH_LOG", s);
            }
        });
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
      
    }
}
