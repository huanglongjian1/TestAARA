package com.android.testmvp;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.android.mvpdemo.mvp.MvpFragment;
import com.android.testmvp.maincontract.Test2Contract;
import com.android.testmvp.maincontract.TestContract;
import com.android.testmvp.viewpage2.Page2_Fragment_Adapter;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class Test2Fragment extends MvpFragment<Test2Contract.Test2Presenter> implements Test2Contract.Test2View {
    List<Fragment> list;

    @Override
    public void initData(Bundle savedInstanceState) {
        list = new ArrayList<>();
        list.add(new MyFragment());
        list.add(new TestFragment());
        list.add(new MyFragment2());
        list.add(new Test3Fragment());
        Page2_Fragment_Adapter page2_fragment_adapter = new Page2_Fragment_Adapter(getChildFragmentManager(), getLifecycle(), list);
        ViewPager2 viewPager2 = rootView.findViewById(R.id.view_pager2_test);
        viewPager2.setAdapter(page2_fragment_adapter);
        BottomNavigationView bottomNavigationView = rootView.findViewById(R.id.BottomNavigationView_fragment);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        viewPager2.setCurrentItem(0);
                        break;
                    case R.id.item2:
                        viewPager2.setCurrentItem(1);
                        break;
                    case R.id.item3:
                        viewPager2.setCurrentItem(2);
                        break;
                }
                BadgeDrawable badgeDrawable=bottomNavigationView.getOrCreateBadge(item.getItemId());
                badgeDrawable.setNumber(99);
                return true;
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
              switch (position){
                  case 0:
                      bottomNavigationView.setSelectedItemId(R.id.item1);
                      break;
                  case 1:
                      bottomNavigationView.setSelectedItemId(R.id.item2);
                      break;
                  case 2:
                      bottomNavigationView.setSelectedItemId(R.id.item3);
                      break;
              }
            }
        });

    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_test_fragment;
    }

    @Override
    protected Test2Contract.Test2Presenter createPresent() {
        return new Test2Contract.Test2Presenter();
    }
}
