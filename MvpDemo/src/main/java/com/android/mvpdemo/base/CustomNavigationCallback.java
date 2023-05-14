package com.android.mvpdemo.base;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;

public class CustomNavigationCallback implements NavigationCallback {
    private static final String TAG = "CustomNavigationCallbac";

    Context mContext;

    public CustomNavigationCallback(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void onFound(Postcard postcard) {
        //路由目标被发现时调用
        Log.e(TAG, "路由目标被发现时调用" );
    }

    @Override
    public void onLost(Postcard postcard) {
        //路由被丢失时调用
        Log.e(TAG, "路由被丢失时调用" );
    }

    @Override
    public void onArrival(Postcard postcard) {
//路由到达之后调用
        Log.e(TAG, "路由到达之后调用" );
    }

    @Override
    public void onInterrupt(Postcard postcard) {
        //路由被拦截时调用
        Log.e(TAG, "路由被拦截时调用" );
    }
}
