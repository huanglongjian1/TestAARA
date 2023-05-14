package com.android.my_mvplibrary.presenter;

import com.android.my_mvplibrary.model.Model;
import com.android.my_mvplibrary.view.View;

public interface Presenter<V,M> {
    void registerView(V view);

    void registerModel(M model);

    V getView();

    void destroy();
}
