package com.valid.pruebatecnica.ui.base;

public class BasePresenter<View extends BaseView> {
    // region Properties
    protected View view;
    // endregion

    // region Constructor
    protected BasePresenter(View view) {
        this.view = view;
    }
    // endregion

    // region Class methods
    void onDetach() { view = null; }
    // endregion
}
