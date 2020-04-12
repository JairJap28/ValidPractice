package com.valid.pruebatecnica.utils;

import androidx.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DiskExecutor implements Executor {
    // region Properties
    private Executor diskExecutor;
    // endregion

    // region Contructor
    public DiskExecutor(){
        diskExecutor = Executors.newSingleThreadExecutor();
    }
    // endregion


    //region Override methods
    @Override
    public void execute(@NonNull Runnable runnable) {
        diskExecutor.execute(runnable);
    }
    // endregion
}
