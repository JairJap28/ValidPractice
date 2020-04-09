package com.valid.pruebatecnica.ui.components.main;

import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.ui.base.BaseView;

import java.util.List;

public interface MainView extends BaseView {
    void showTracks(List<Track> traks);
    void showErrorMessage();
    void showThereIsNoTracks();
}
