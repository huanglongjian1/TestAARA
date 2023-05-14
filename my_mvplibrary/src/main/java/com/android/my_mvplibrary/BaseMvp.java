package com.android.my_mvplibrary;

import com.android.my_mvplibrary.model.Model;
import com.android.my_mvplibrary.presenter.Presenter;
import com.android.my_mvplibrary.test.mvp.MainModelImpl;
import com.android.my_mvplibrary.test.mvp.MainPresenter;
import com.android.my_mvplibrary.view.View;

public interface BaseMvp<M,V,P> {
    M createModel();

    V createView();

    P createPresenter();
}
