package com.valid.pruebatecnica.ui.base;

import android.app.Application;

import com.valid.pruebatecnica.App;

public class BasePresenter<View extends BaseView> {
    protected View view;

    protected BasePresenter(View view) {
        this.view = view;
    }

    void onDetach() { view = null; }
}
