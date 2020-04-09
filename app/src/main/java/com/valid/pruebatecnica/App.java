package com.valid.pruebatecnica;

import android.app.Application;

public class App extends Application {
    private static App mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    private static void init(App app) { mInstance = app; }

    public static App getInstance() { return mInstance; }
}
