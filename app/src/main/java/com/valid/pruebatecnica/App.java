package com.valid.pruebatecnica;

import android.app.Application;

import com.valid.pruebatecnica.di.AppComponent;
import com.valid.pruebatecnica.di.DaggerAppComponent;
import com.valid.pruebatecnica.di.RepositoryModule;

public class App extends Application {
    private static App mInstance;
    private AppComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }

    private void init(App app) {
        mInstance = app;
        initInject();
    }

    private void initInject(){
        mainComponent = DaggerAppComponent.builder()
                .repositoryModule(new RepositoryModule())
                .build();

    }

    public AppComponent getMainComponent(){
        return mainComponent;
    }

    public static App getInstance() { return mInstance; }
}
