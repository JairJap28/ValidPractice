package com.valid.pruebatecnica.data.source.artist.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.valid.pruebatecnica.data.entity.Artist;

import java.util.List;

@Dao
public interface ArtistDao {
    @Query("SELECT * FROM artists")
    List<Artist> getAllArtists();

    @Query("SELECT * FROM artists WHERE artist_id=:id LIMIT 1")
    Artist getArtistById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArtist(Artist artist);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllArtists(List<Artist> artists);

    @Query("DELETE FROM artists")
    void deleteAllArtists();
}
