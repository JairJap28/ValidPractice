package com.valid.pruebatecnica.di;


import com.valid.pruebatecnica.ui.components.detail.DetailsActivity;
import com.valid.pruebatecnica.ui.components.detail.DetailsPresenter;
import com.valid.pruebatecnica.ui.components.main.MainActivity;
import com.valid.pruebatecnica.ui.components.main.MainPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {RepositoryModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);
    void inject(DetailsActivity detailsActivity);
    void inject(MainPresenter mainPresenter);
    void inject(DetailsPresenter detailsPresenter);
}
