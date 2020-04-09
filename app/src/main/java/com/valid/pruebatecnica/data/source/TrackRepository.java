package com.valid.pruebatecnica.data.source;

import com.valid.pruebatecnica.data.entity.Track;

import java.util.List;

public class TrackRepository implements TrackDataSource {

    private final TrackDataSource trackRemote;
    private final TrackDataSource trackLocal;
    private final TrackDataSource trackCache;

    private static TrackRepository mInstance;

    private TrackRepository(TrackDataSource trackRemote, TrackDataSource trackLocal, TrackDataSource trackCache) {
        this.trackRemote = trackRemote;
        this.trackLocal = trackLocal;
        this.trackCache = trackCache;
    }

    public static TrackRepository getInstance(TrackDataSource trackRemote,
                                       TrackDataSource trackLocal,
                                       TrackDataSource trackCache){
        if(mInstance == null) {
            mInstance = new TrackRepository(trackRemote, trackLocal, trackCache);
        }
        return mInstance;
    }

    @Override
    public void getTracks(LoadTracksCallack callack) {
        if(callack == null) return;
        trackCache.getTracks(new LoadTracksCallack() {
            @Override
            public void onTrackLoaded(List<Track> tracks) {
                callack.onTrackLoaded(tracks);
            }

            @Override
            public void onDataNotAvailable() {
                getTracksFromLocalDataSource(callack);
            }

            @Override
            public void onError() {
                callack.onError();
            }
        });
    }

    @Override
    public void saveTrack(List<Track> tracks) {
        trackLocal.saveTrack(tracks);
    }

    private void getTracksFromLocalDataSource(final LoadTracksCallack callback) {
        trackLocal.getTracks(new LoadTracksCallack() {
            @Override
            public void onTrackLoaded(List<Track> tracks) {
                callback.onTrackLoaded(tracks);
                refreshCache(tracks);
            }

            @Override
            public void onDataNotAvailable() {
                getTracksFromRemoteDataSource(callback);
            }

            @Override
            public void onError() {

            }
        });
    }

    private void getTracksFromRemoteDataSource(final LoadTracksCallack callack){
        trackRemote.getTracks(new LoadTracksCallack() {
            @Override
            public void onTrackLoaded(List<Track> tracks) {
                callack.onTrackLoaded(tracks);
                saveTrack(tracks);
                refreshCache(tracks);
            }

            @Override
            public void onDataNotAvailable() {
                callack.onDataNotAvailable();
            }

            @Override
            public void onError() {
                callack.onError();
            }
        });
    }

    private void refreshCache(List<Track> tracks) { trackCache.saveTrack(tracks); }
    public void destroyInstance(){
        mInstance = null;
    }
}
