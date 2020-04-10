package com.valid.pruebatecnica.data.source.geo.repository;

import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.ImageTrack;
import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.artist.repository.ArtistDataSource;
import com.valid.pruebatecnica.data.source.artistTrack.repository.ArtistTrackDataSource;
import com.valid.pruebatecnica.data.source.track.repository.TrackDataSource;
import com.valid.pruebatecnica.data.source.geo.remote.model.ImageRemote;
import com.valid.pruebatecnica.data.source.geo.remote.model.TrackRemote;
import com.valid.pruebatecnica.data.source.geo.remote.model.TrackResponse;

import java.util.ArrayList;
import java.util.List;

public class GeoRepository implements GeoDataSource {

    private final GeoDataSource geoRemote;
    private final TrackDataSource trackLocal;
    private final ArtistDataSource artistLocal;
    private final ArtistTrackDataSource artistTrackDataSource;

    private static GeoRepository mInstance;

    private GeoRepository(GeoDataSource geoRemote, TrackDataSource trackLocal, ArtistDataSource artistLocal, ArtistTrackDataSource artistTrackDataSource) {
        this.geoRemote = geoRemote;
        this.trackLocal = trackLocal;
        this.artistLocal = artistLocal;
        this.artistTrackDataSource = artistTrackDataSource;
    }

    public static GeoRepository getInstance(GeoDataSource trackRemote, TrackDataSource trackLocal, ArtistDataSource artistLocal, ArtistTrackDataSource artistTrackDataSource) {
        if(mInstance == null) {
            mInstance = new GeoRepository(trackRemote, trackLocal, artistLocal, artistTrackDataSource);
        }
        return mInstance;
    }

    public void destroyInstance(){
        mInstance = null;
    }

    @Override
    public void getListData(LoadListCallback<TrackResponse> callback) {
        geoRemote.getListData(new LoadListCallback<TrackResponse>() {
            @Override
            public void onLoaded(List<TrackResponse> list) {
                saveListData(list);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    @Override
    public void saveListData(List<TrackResponse> list) {
        TrackResponse trackResponse = list.get(0);

        List<Track> tracks = new ArrayList<>();
        List<Artist> artists = new ArrayList<>();
        List<ImageTrack> imageTracks = new ArrayList<>();

        for (TrackRemote trackRemote: trackResponse.getTracks().getTrack()) {
            tracks.add(new Track(trackRemote.getMbid(), trackRemote.getName(), trackRemote.getDuration(), trackRemote.getListeners(), trackRemote.getUrl(), trackRemote.getAttr().getRank()));
            artists.add(trackRemote.getArtist());
            for (ImageRemote image: trackRemote.getImage()) {
                imageTracks.add(new ImageTrack(trackRemote.getMbid(), image.getText(), image.getSize()));
            }
        }
        trackLocal.saveListData(tracks);
        artistLocal.saveListData(artists);
        artistTrackDataSource.saveListData(null);
        // imageTrack
        // artistLocal
        // artistTrack
    }

    @Override
    public void getData(LoadSingleCallback<TrackResponse> callback) {

    }

    @Override
    public void saveData(TrackResponse object) {

    }
}
