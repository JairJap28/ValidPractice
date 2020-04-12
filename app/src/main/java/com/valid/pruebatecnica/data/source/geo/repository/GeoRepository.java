package com.valid.pruebatecnica.data.source.geo.repository;

import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.ArtistTrack;
import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.artist.repository.ArtistDataSource;
import com.valid.pruebatecnica.data.source.artistTrack.repository.ArtistTrackDataSource;
import com.valid.pruebatecnica.data.source.geo.remote.model.TrackRemote;
import com.valid.pruebatecnica.data.source.geo.remote.model.TrackResponse;
import com.valid.pruebatecnica.data.source.track.repository.TrackDataSource;

import java.util.ArrayList;
import java.util.List;

public class GeoRepository implements GeoDataSource {

    // region Properties
    private final GeoDataSource geoRemote;
    private final TrackDataSource trackLocal;
    private final ArtistDataSource artistLocal;
    private final ArtistTrackDataSource artistTrackLocal;
    private static GeoRepository mInstance;
    // endregion

    // region Constructor
    private GeoRepository(GeoDataSource geoRemote, TrackDataSource trackLocal, ArtistDataSource artistLocal,
                          ArtistTrackDataSource artistTrackLocal) {
        this.geoRemote = geoRemote;
        this.trackLocal = trackLocal;
        this.artistLocal = artistLocal;
        this.artistTrackLocal = artistTrackLocal;
    }
    // endregion

    // region Class methods
    public static GeoRepository getInstance(GeoDataSource trackRemote, TrackDataSource trackLocal, ArtistDataSource artistLocal,
                                            ArtistTrackDataSource artistTrackDataSource) {
        if(mInstance == null) {
            mInstance = new GeoRepository(trackRemote, trackLocal, artistLocal, artistTrackDataSource);
        }
        return mInstance;
    }
    // endregion

    // region Override methods
    @Override
    public void getListData(LoadListCallback<TrackResponse> callback, int page) {
        geoRemote.getListData(new LoadListCallback<TrackResponse>() {
            @Override
            public void onLoaded(List<TrackResponse> list) {
                if(list.get(0) == null || list.get(0).getTracks() == null){
                    callback.onError();
                } else {
                    saveListData(list);
                    callback.onLoaded(list);
                }
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        }, page);
    }

    @Override
    public void saveListData(List<TrackResponse> list) {
        TrackResponse trackResponse = list.get(0);

        List<Track> tracks = new ArrayList<>();
        List<Artist> artists = new ArrayList<>();
        List<ArtistTrack> artistTracks = new ArrayList<>();

        for (TrackRemote trackRemote: trackResponse.getTracks().getTrack()) {
            tracks.add(new Track(trackRemote.getMbid(), trackRemote.getName(), trackRemote.getDuration(),
                    trackRemote.getListeners(), trackRemote.getUrl(), trackRemote.getAttr().getRank(), trackRemote.getImage().get(0).getText()));
            artists.add(trackRemote.getArtist());
            artistTracks.add(new ArtistTrack(trackRemote.getArtist().getMbid(), trackRemote.getMbid()));
        }
        trackLocal.saveListData(tracks);
        artistLocal.saveListData(artists);
        artistTrackLocal.saveListData(artistTracks);
    }

    @Override
    public void getData(LoadSingleCallback<TrackResponse> callback) {

    }

    @Override
    public void saveData(TrackResponse object) {

    }
    // endregion
}
