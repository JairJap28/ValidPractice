package com.valid.pruebatecnica.ui.components.main;

import androidx.lifecycle.ViewModel;

import com.valid.pruebatecnica.data.entity.Track;

import java.util.List;

public class MainActivityData extends ViewModel {
    // region Properties
    private int page = 1;
    private int numResults;
    private boolean isLoading = false;
    private List<Track> traks;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    int getNumResults() {
        return numResults;
    }

    void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    boolean isLoading() {
        return isLoading;
    }

    void setLoading(boolean loading) {
        isLoading = loading;
    }

    List<Track> getTraks() {
        return traks;
    }

    void setTraks(List<Track> traks) {
        this.traks = traks;
    }
    // endregion

    // region Class methods
    void restart(){
        this.page = 1;
        this.numResults = 0;
    }
    // endregion
}
