package com.valid.pruebatecnica.ui.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.valid.pruebatecnica.App;

public abstract class BaseActivity<Presenter extends BasePresenter> extends AppCompatActivity {
    protected Presenter presenter;
    protected abstract void initInject();
    private BaseView view;

    @NonNull
    protected abstract Presenter createPresenter();

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
}
