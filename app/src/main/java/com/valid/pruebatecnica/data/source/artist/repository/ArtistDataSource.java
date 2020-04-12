package com.valid.pruebatecnica.data.source.artist.repository;

import com.valid.pruebatecnica.data.base.DataStoreBase;
import com.valid.pruebatecnica.data.entity.Artist;

public interface ArtistDataSource extends DataStoreBase<Artist> {
    void getArtistsByIdTrack(LoadSingleCallback<Artist> callback, String trackMbId);

    void deleteAll();
}
