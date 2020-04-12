package com.valid.pruebatecnica.ui.components.detail;

import com.valid.pruebatecnica.data.base.DataStoreBase;
import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.source.artistTrack.repository.ArtistTrackRepository;
import com.valid.pruebatecnica.ui.base.BasePresenter;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

public class DetailsPresenter extends BasePresenter<DetailsView> {

    // region Properties
    @Inject ArtistTrackRepository artistTrackRepository;

    private String mbIdTrack;

    public void setMbIdTrack(String mbIdTrack) {
        this.mbIdTrack = mbIdTrack;
    }
    // endregion

    // region Constructor
    DetailsPresenter(DetailsView view) {
        super(view);
    }
    // endregion

    // region Class methods
    public void onAttach() { getArtistsBiId(); }

    private void getArtistsBiId() {
        artistTrackRepository.getArtistByIdTrack(new ArtistTrackCall(view), mbIdTrack);
    }

    private void getNumberSongs(String mbIdArtist){
        artistTrackRepository.getNumberSongsArtist(new NumberSongsTrackCall(view), mbIdArtist);
    }
    // endregion

    // region Class listeners
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

        }

        @Override
        public void onError() {

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

        }

        @Override
        public void onError() {

        }
    }
    // endregion
}
