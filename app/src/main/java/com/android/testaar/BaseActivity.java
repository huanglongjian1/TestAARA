package com.android.testaar;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Activity基类
 * @author Donkor
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

//将Activity实例添加到AppManager的堆栈

        AppManager.getInstance().addActivity(this);

    }

    @Override

    protected void onDestroy() {

        super.onDestroy();

//将Activity实例从AppManager的堆栈中移除

        AppManager.getInstance().removeActivity(this);

    }

}