package com.valid.pruebatecnica.ui.components.main;

import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.TrackDataSource;
import com.valid.pruebatecnica.data.source.TrackRepository;
import com.valid.pruebatecnica.ui.base.BasePresenter;

import java.lang.ref.WeakReference;
import java.util.List;

public class MainPresenter extends BasePresenter<MainView> {

    private TrackRepository trackRepository;

    MainPresenter(MainView mainView, TrackRepository trackRepository) {
        super(mainView);
        this.trackRepository = trackRepository;
    }

    protected MainPresenter(MainView view) {
        super(view);
    }

    public void onAttach() {  }

    private void getAllTracks() {
        trackRepository.getTracks(new TrackCallListener());
    }

    private static class TrackCallListener implements TrackDataSource.LoadTracksCallack {

        private WeakReference<MainView> view;

        @Override
        public void onTrackLoaded(List<Track> tracks) {
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
