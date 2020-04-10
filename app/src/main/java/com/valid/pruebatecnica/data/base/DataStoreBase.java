package com.valid.pruebatecnica.data.base;

import java.util.List;

public interface DataStoreBase<T> {
    interface LoadListCallback<T> {
        void onLoaded(List<T> list);
        void onDataNotAvailable();
        void onError();
    }

    interface LoadSingleCallback<T> {
        void onLoaded(T object);
        void onDataNotAvailable();
        void onError();
    }

    void getListData(LoadListCallback<T> callback);
    void saveListData(List<T> list);
    void getData(LoadSingleCallback<T> callback);
    void saveData(T object);
}
