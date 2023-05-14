package com.android.testmvp.dagger2;

import com.android.testmvp.MainActivity;
import com.android.testmvp.TestSchemeActivity;

import dagger.Component;

@Component(modules = NetModule.class)
public interface ApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(TestSchemeActivity testSchemeActivity);
}
