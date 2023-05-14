package com.android.mvpdemo.base;


import android.os.Bundle;

/**
 * UI回调接口
 * @author llw
 */
public interface IUiCallback {

    void initBeforeView(Bundle savedInstanceState);

    //初始化视图
    void initData(Bundle savedInstanceState);

    //获取布局Id
    int getLayoutId();

}


