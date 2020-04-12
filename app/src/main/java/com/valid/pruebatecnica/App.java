package com.valid.pruebatecnica;

import android.app.Application;

import com.valid.pruebatecnica.di.AppComponent;
import com.valid.pruebatecnica.di.DaggerAppComponent;
import com.valid.pruebatecnica.di.RepositoryModule;

public class App extends Application {
    // region properties
    private static App mInstance;
    private AppComponent mainComponent;

    public static App getInstance() {
        return mInstance;
    }

    public AppComponent getMainComponent() {
        return mainComponent;
    }
    // endregion

    // region Override methods
    @Override
    public void onCreate() {
        super.onCreate();
        init(this);
    }
    // endregion

    // region Methods class
    private void init(App app) {
        mInstance = app;
        initInject();
    }

    private void initInject(){
        mainComponent = DaggerAppComponent.builder()
                .repositoryModule(new RepositoryModule())
                .build();

    }
    // endregion
}
