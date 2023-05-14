package com.android.testmvp.dagger2;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.template.IProvider;

public interface IFragmentService extends IProvider {
    Fragment createFragment();
}
