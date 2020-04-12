package com.valid.pruebatecnica.ui.components.main;

import com.valid.pruebatecnica.data.base.DataStoreBase;
import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.geo.remote.model.TrackResponse;
import com.valid.pruebatecnica.data.source.geo.repository.GeoRepository;
import com.valid.pruebatecnica.data.source.track.repository.TrackRepository;
import com.valid.pruebatecnica.ui.base.BasePresenter;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainView> {
    // region Properties
    @Inject GeoRepository geoRepository;
    @Inject TrackRepository trackRepository;

    private int page;

    public void setPage(int page) {
        this.page = page;
    }
    // endregion

    // region Constructor
    MainPresenter(MainView view) {
        super(view);
    }
    // endregion

    // region Class methods
    public void onAttach() { getAllRemoteTracks(); }

    private void getAllRemoteTracks() {
        geoRepository.getListData(new GeoCallListener(view), page);
    }

    private void getLocalTracks() {
        trackRepository.getListData( new TrackCallListener(view), page);
    }

    public void deleteAllTracks() {
        trackRepository.deleteAll();
        getAllRemoteTracks();
    }
    // endregion

    // region Class listener
    private class GeoCallListener implements DataStoreBase.LoadListCallback<TrackResponse> {

        private WeakReference<MainView> view;

        GeoCallListener(MainView view) {
            this.view = new WeakReference<>(view);
        }

        @Override
        public void onLoaded(List<TrackResponse> tracks) {
            if(view.get() == null) return;
            getLocalTracks();
        }

        @Override
        public void onDataNotAvailable() {
            if(view.get() == null) return;
            getLocalTracks();
        }

        @Override
        public void onError() {
            if(view.get() == null) return;
            getLocalTracks();
        }
    }

    private class TrackCallListener implements DataStoreBase.LoadListCallback<Track>{

        WeakReference<MainView> view;

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
    // endregion
}
