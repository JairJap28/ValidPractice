package com.valid.pruebatecnica.ui.components.main;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.valid.pruebatecnica.R;
import com.valid.pruebatecnica.data.DataManager;
import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.data.source.TrackDataSource;
import com.valid.pruebatecnica.data.source.TrackRepository;
import com.valid.pruebatecnica.ui.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, TrackAdapter.TrackListener {

    private TrackAdapter trackAdapter;

    @BindView(R.id.recycler_view_tracks)
    RecyclerView recyclerViewTracks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        recyclerViewTracks.setLayoutManager(new LinearLayoutManager(this));
        trackAdapter = new TrackAdapter();
        trackAdapter.setListener(this);
        presenter.onAttach();
    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        TrackRepository trackRepository = DataManager.getInstance().getTrackRepository();
        return new MainPresenter(this, trackRepository);
    }

    @Override
    public void showTracks(List<Track> traks) {
        trackAdapter.submitList(traks);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Server error!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showThereIsNoTracks() {
        Toast.makeText(this, "There is no tracks!!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTrackClicked(Track track) {

    }
}
