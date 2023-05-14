package com.android.testaar.mvptest.app;

import com.android.mvplibrary.BaseApplication;
import com.android.mvplibrary.network.utils.NetworkApi;

public class MyApp extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        NetworkApi.init(new NetworkRequiredInfo(this));
    }
}
