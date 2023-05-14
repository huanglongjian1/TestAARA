package com.android.my_mvplibrary;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.android.my_mvplibrary.model.Model;
import com.android.my_mvplibrary.presenter.Presenter;
import com.android.my_mvplibrary.view.View;

public abstract class BaseMvpFragment<M extends Model, V extends View, P extends Presenter> extends Fragment implements BaseMvp<M, V, P> {
    protected P presenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        presenter =createPresenter();
        if (presenter != null) {
            presenter.registerModel(createModel());
            presenter.registerView(createView());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (presenter != null) {
            presenter.destroy();
        }
    }
}
