package com.valid.pruebatecnica.ui.components.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.valid.pruebatecnica.ui.base.BaseActivity;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    protected DetailsPresenter createPresenter() {
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
