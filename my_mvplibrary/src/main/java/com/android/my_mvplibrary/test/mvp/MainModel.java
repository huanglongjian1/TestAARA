package com.android.my_mvplibrary.test.mvp;

import com.android.my_mvplibrary.model.Model;

import io.reactivex.rxjava3.core.Observable;

public interface MainModel extends Model {
    Observable getDataFromNetString();

    String getDataFromNet();

    void stopRequest();
}
