package com.valid.pruebatecnica.ui.components.main;

import com.valid.pruebatecnica.data.base.DataStoreBase;
import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.geo.repository.GeoRepository;
import com.valid.pruebatecnica.ui.base.BasePresenter;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainPresenter extends BasePresenter<MainView> {

    private GeoRepository geoRepository;

    MainPresenter(MainView mainView, GeoRepository geoRepository) {
        super(mainView);
        this.geoRepository = geoRepository;
    }

    protected MainPresenter(MainView view) {
        super(view);
    }

    public void onAttach() { getAllTracks(); }

    private void getAllTracks() {
        geoRepository.getListData(new TrackCallListener(view));
    }

    private static class TrackCallListener implements DataStoreBase.LoadListCallback {

        private WeakReference<MainView> view;

        TrackCallListener(MainView view) {
            this.view = new WeakReference<>(view);
        }

        @Override
        public void onLoaded(List<Track> tracks) {
            if(view.get() == null) return;
            view.get().showTracks(tracks);
        }

        @Override
        public void onDataNotAvailable() {
            if(view.get() == null) return;
            view.get().showThereIsNoTracks();
        }

        @Override
        public void onError() {
            if(view.get() == null) return;
            view.get().showErrorMessage();
        }
    }
}
