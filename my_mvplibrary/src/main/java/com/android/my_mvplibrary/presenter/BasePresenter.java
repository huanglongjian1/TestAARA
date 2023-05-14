package com.android.my_mvplibrary.presenter;

import com.android.my_mvplibrary.model.Model;
import com.android.my_mvplibrary.view.View;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V,M> implements Presenter<V,M> {
    private WeakReference wrf;
    protected M model;

    @Override
    public void registerView(V view) {
        wrf = new WeakReference(view);
    }

    @Override
    public void registerModel(M model) {
        this.model = model;
    }

    @Override
    public V getView() {
        return wrf == null ? null : (V) wrf.get();
    }

    @Override
    public void destroy() {
        if (wrf != null) {
            wrf.clear();
            wrf = null;
        }
    }

    protected abstract void onViewDestroy();
}
