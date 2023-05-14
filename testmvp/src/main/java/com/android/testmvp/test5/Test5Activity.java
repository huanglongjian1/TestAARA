package com.android.testmvp.test5;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.android.testmvp.Loge;
import com.android.testmvp.R;
import com.android.testmvp.util.Constants;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

@Route(path = Constants.TEST5_ACTIVITY)
public class Test5Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test6);


        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);

        NavController navController = navHostFragment.getNavController();
        Bundle bundle = new Bundle();
        bundle.putString("key", "val");
        navController.setGraph(R.navigation.demo_nav, bundle);

        navController.navigate(R.id.item1);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item1:
                        navController.navigate(R.id.item1);
                        break;
                    case R.id.item2:
                        navController.navigate(R.id.item2);
                        break;
                    case R.id.item3:
                        navController.navigate(R.id.item3);
                        break;
                }


                return true;
            }
        });

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController navController, @NonNull NavDestination navDestination, @Nullable Bundle bundle) {
                Loge.e(navDestination.getLabel().toString());
                Menu menu = bottomNavigationView.getMenu();
                switch (navDestination.getLabel().toString()) {
                    case "BlankFragment1":
                        menu.getItem(0).setChecked(true);
                        break;
                    case "BlankFragment2":
                        menu.getItem(1).setChecked(true);
                        break;
                    case "BlankFragment3":
                        menu.getItem(2).setChecked(true);
                        break;
                }

            }

        });

    }
}
