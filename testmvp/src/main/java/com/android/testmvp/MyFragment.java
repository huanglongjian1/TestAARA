package com.android.testmvp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.mvpdemo.mvp.MvpFragment;
import com.android.testmvp.maincontract.MainContract;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MyFragment extends MvpFragment<MainContract.MainPresenter> implements MainContract.IMainView {
    @Override
    public void initData(Bundle savedInstanceState) {
        BottomNavigationView bnv_001 = (BottomNavigationView) rootView.findViewById(R.id.bnv_001);

        TextView tv_whichItemSelected = rootView.findViewById(R.id.tv_whichItemSelected);


        bnv_001.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                tv_whichItemSelected.setText(item.getTitle());
                return true;
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.my_fragment_layout2;
    }

    @Override
    protected MainContract.MainPresenter createPresent() {
        return new MainContract.MainPresenter();
    }

    @Override
    public void getWallPaper(Object wallPaperResponse) {

    }

    @Override
    public void getWallPaperFailed(Throwable e) {

    }
}
