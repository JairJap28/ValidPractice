package com.valid.pruebatecnica.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskExecutor implements Executor {
    private Executor diskExecutor;

    public DiskExecutor(){
        diskExecutor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void execute(Runnable runnable) {
        diskExecutor.execute(runnable);
    }
}
