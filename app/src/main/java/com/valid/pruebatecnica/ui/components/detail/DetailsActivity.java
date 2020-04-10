package com.valid.pruebatecnica.ui.components.detail;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.ui.base.BaseActivity;

import butterknife.ButterKnife;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView  {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
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
    
    public static void start(Context context, Track track){
        Toast.makeText(context, "Detail activity started", Toast.LENGTH_SHORT).show();
    }
}
