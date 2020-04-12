package com.valid.pruebatecnica.ui.base;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity {
    // region Properties
    protected Presenter presenter;
    // endregion

    @NonNull
    protected abstract Presenter createPresenter();

    // region Override methods
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = createPresenter();
        initInject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDetach();
    }
    // endregion

    // region Class methods
    protected abstract void initInject();
    // endregion
}
