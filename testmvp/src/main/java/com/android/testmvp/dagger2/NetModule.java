package com.android.testmvp.dagger2;

import com.android.testmvp.bean.User;
import com.android.testmvp.network.NetApi;
import com.android.testmvp.network.RetrofitUtils;

import dagger.Module;
import dagger.Provides;

@Module
public class NetModule {


    //此处的retrofit就是provideRetrofit()提供的，它会优先在当前的module
    //中查找已经存在的实例，然后使用
    //@MyScope
    @Provides
    public NetApi provideApiService() {
        return RetrofitUtils.create(NetApi.class, NetApi.BASE_URL_PHONE);
    }

    @Provides
    public User provideUser() {
        return new User("hlj", 18);
    }


}
