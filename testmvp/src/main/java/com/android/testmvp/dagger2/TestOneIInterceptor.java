package com.android.testmvp.dagger2;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.testmvp.Loge;
import com.android.testmvp.app.MyApp;
import com.android.testmvp.util.Constants;

@Interceptor(priority = 1, name = "拦截未登录")
public class TestOneIInterceptor implements IInterceptor {
    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        Loge.e("开始拦截了");
        // ARouter.getInstance().build(Constants.OTHER_ACTIVITY).navigation();
        // 这里的弹窗仅做举例，代码写法不具有可参考价值

        if (postcard.getPath() == Constants.TEST_SCHEME_ACTIVITY) {
            Loge.e(Constants.TEST_SCHEME_ACTIVITY+"-----------");
            postcard.setPath(Constants.OTHER_ACTIVITY);
            callback.onContinue(postcard);
          //  ARouter.getInstance().build(Constants.OTHER_ACTIVITY).navigation();
          //  callback.onInterrupt(new Throwable());
        } else {
            callback.onContinue(postcard);
        }
    }

    Callback mcallback;

    public void setcallback(Callback callback) {
        mcallback = callback;
    }

    public interface Callback {
        void call();
    }

    @Override
    public void init(Context context) {
        Loge.e("准备拦截了");
    }
}