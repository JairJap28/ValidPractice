package com.valid.pruebatecnica.data.entity;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "ArtistTrack",
        indices = {@Index(value = {"idArtist", "idTrack"}, unique = true)}
)
public class ArtistTrack {
    // region Properties
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String idArtist;

    private String idTrack;

    public ArtistTrack(String idArtist, String idTrack) {
        this.idArtist = idArtist;
        this.idTrack = idTrack;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdArtist() {
        return idArtist;
    }

    public String getIdTrack() {
        return idTrack;
    }
    // endregion
}
