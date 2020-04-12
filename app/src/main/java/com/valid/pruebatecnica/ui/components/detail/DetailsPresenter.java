package com.valid.pruebatecnica.ui.components.detail;

import com.valid.pruebatecnica.data.base.DataStoreBase;
import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.source.artistTrack.repository.ArtistTrackRepository;
import com.valid.pruebatecnica.ui.base.BasePresenter;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

public class DetailsPresenter extends BasePresenter<DetailsView> {

    @Inject ArtistTrackRepository artistTrackRepository;

    private String mbIdTrack;

    protected DetailsPresenter(DetailsView view) {
        super(view);
    }

    public void setMbIdTrack(String mbIdTrack) {
        this.mbIdTrack = mbIdTrack;
    }

    public void onAttach() { getArtistsBiId(); }

    private void getArtistsBiId() {
        artistTrackRepository.getArtistByIdTrack(new ArtistTrackCall(view), mbIdTrack);
    }

    private void getNumberSongs(String mbIdArtist){
        artistTrackRepository.getNumberSongsArtist(new NumberSongsTrackCall(view), mbIdArtist);
    }

    private class ArtistTrackCall implements DataStoreBase.LoadSingleCallback<Artist> {

        private WeakReference<DetailsView> detailsView;

        ArtistTrackCall(DetailsView detailsView) {
            this.detailsView = new WeakReference<>(detailsView);
        }

        @Override
        public void onLoaded(Artist artist) {
            if(detailsView == null) return;
            getNumberSongs(artist.getMbid());
            detailsView.get().showArtist(artist);
        }

        @Override
        public void onDataNotAvailable() {
            if(detailsView == null) return;
        }

        @Override
        public void onError() {
            if(detailsView == null) return;
        }
    }

    private class NumberSongsTrackCall implements DataStoreBase.LoadSingleCallback<Integer> {

        private WeakReference<DetailsView> detailsView;

        NumberSongsTrackCall(DetailsView detailsView) {
            this.detailsView = new WeakReference<>(detailsView);
        }

        @Override
        public void onLoaded(Integer numberSongs) {
            if(detailsView == null) return;
            detailsView.get().showNumberTracks(numberSongs);
        }

        @Override
        public void onDataNotAvailable() {
            if(detailsView == null) return;
        }

        @Override
        public void onError() {
            if(detailsView == null) return;
        }
    }
}
