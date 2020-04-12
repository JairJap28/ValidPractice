package com.valid.pruebatecnica.data.source.artistTrack.repository;

import com.valid.pruebatecnica.data.base.DataStoreBase;
import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.ArtistTrack;

public interface ArtistTrackDataSource extends DataStoreBase<ArtistTrack> {
    void getArtistByIdTrack(LoadSingleCallback<Artist> listCallback, String mbId);
    void getNumberSongsArtist(LoadSingleCallback<Integer> callback, String mbId);
}
