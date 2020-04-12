package com.valid.pruebatecnica.ui.components.detail;

import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.ui.base.BaseView;

public interface DetailsView extends BaseView {
    void showNumberTracks(int numberTracks);
    void showArtist(Artist artist);
}
