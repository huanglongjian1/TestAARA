package com.android.testmvp.dagger2;

import android.content.Context;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.DegradeService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.testmvp.Loge;
import com.android.testmvp.util.Constants;

import okhttp3.logging.HttpLoggingInterceptor;


@Route(path = "/degrade/Service")
public class DegradeServiceImpl implements DegradeService {
    private static final String TAG = DegradeServiceImpl.class.getName();
    private Context mContext;
    @Override
    public void onLost(Context context, Postcard postcard) {
        Loge.e(postcard.toString());
        ARouter.getInstance()
                .build(Constants.OTHER_ACTIVITY)
                .withString("MAIN_FRAGMENT_TYPE", "FRAGMENT_URL_LOGIN")
                .navigation();
    }
    @Override
    public void init(Context context) {
        mContext = context;
    }
}