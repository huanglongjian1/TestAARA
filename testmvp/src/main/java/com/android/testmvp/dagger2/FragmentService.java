package com.android.testmvp.dagger2;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvp.Loge;
import com.android.testmvp.MyFragment;
import com.android.testmvp.util.Constants;

@Route(path = Constants.MY_FRAGMENT)
public class FragmentService implements IFragmentService{
    @Override
    public Fragment createFragment() {
        return new MyFragment();
    }

    @Override
    public void init(Context context) {
        Loge.e(context.toString());
    }
}
