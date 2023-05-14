package com.android.testmvp.app;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.mvpdemo.BaseApplication;

import java.lang.ref.WeakReference;

public class MyApp extends BaseApplication {
    private static WeakReference<Activity> sTopActivity;

    public static Activity getTopActivity() {
        return sTopActivity == null ? null : sTopActivity.get();
    }

    private static boolean isDebug = true;

    @Override
    public void onCreate() {
        super.onCreate();
        if (isDebug) {
            //打印日志
            ARouter.openLog();
            //开始调试
            ARouter.openDebug();
        }
        //ARouter的实例化
        ARouter.init(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                sTopActivity = new WeakReference<>(activity);
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {
                if (activity == getTopActivity()) {
                    sTopActivity = null;
                }
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
