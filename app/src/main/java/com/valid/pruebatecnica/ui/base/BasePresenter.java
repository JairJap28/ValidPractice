package com.valid.pruebatecnica.ui.base;

public class BasePresenter<View extends BaseView> {
    protected View view;

    protected BasePresenter(View view) { this.view = view; }
    void onDetach() { view = null; }
}
