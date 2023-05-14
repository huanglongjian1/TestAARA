package com.android.testmvp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.mvpdemo.mvp.MvpActivity;
import com.android.testmvp.maincontract.Test2Contract;
import com.android.testmvp.test4.Blank2Fragment;
import com.android.testmvp.test4.Blank3Fragment;
import com.android.testmvp.test4.BlankFragment;
import com.android.testmvp.util.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

@Route(path = Constants.TEST4_ACTIVITY)
public class Test4Activity extends MvpActivity<Test2Contract.Test2Presenter> implements Test2Contract.Test2View {
    private FragmentTransaction fragmentTransaction;
    private Fragment fragment1, fragment2, fragment3;

    @Override
    public void initData(Bundle savedInstanceState) {
        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView_fragment_test4);
        fragment1 = new BlankFragment();
        fragment2 = new Blank2Fragment();
        fragment3 = new Blank3Fragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.nav_host_fragment, fragment1);
        fragmentTransaction.add(R.id.nav_host_fragment, fragment2);
        fragmentTransaction.add(R.id.nav_host_fragment, fragment3);
        fragmentTransaction.commit();
        showFragment(fragment1);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        showFragment(fragment1);
                        break;
                    case R.id.item2:
                        showFragment(fragment2);
                        break;
                    case R.id.item3:
                        showFragment(fragment3);
                        break;
                }
                return true;
            }
        });
    }

    private void hideFragment() {
        if (null != fragment1) {
            fragmentTransaction.hide(fragment1);
        }
        if (null != fragment2) {
            fragmentTransaction.hide(fragment2);
        }
        if (null != fragment3) {
            fragmentTransaction.hide(fragment3);
        }
    }

    private void showFragment(Fragment fragment) {
        if (null != fragment) {
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            hideFragment();
            fragmentTransaction.show(fragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.test4_activity_layout;
    }

    @Override
    protected Test2Contract.Test2Presenter createPresenter() {
        return new Test2Contract.Test2Presenter();
    }
}
