package com.valid.pruebatecnica.ui.components.main;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.valid.pruebatecnica.App;
import com.valid.pruebatecnica.R;
import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.ui.base.BaseActivity;
import com.valid.pruebatecnica.ui.base.Navegador;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.co.recruit_lifestyle.android.widget.WaveSwipeRefreshLayout;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView, TrackAdapter.TrackListener {


    private TrackAdapter trackAdapter;

    @BindView(R.id.my_toolbar)
    Toolbar myToolbar;

    @BindView(R.id.text_view_numer_records)
    TextView numberRecords;

    @BindView(R.id.recycler_view_tracks)
    RecyclerView recyclerViewTracks;

    @BindView(R.id.main_swipe)
    WaveSwipeRefreshLayout mWaveSwipeRefreshLayout;

    @BindView(R.id.layout_no_records)
    LinearLayout layoutNoRecords;

    @BindView(R.id.progress_circular_search)
    ProgressBar progressBarSearch;

    MainActivityData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(myToolbar);
        data = new ViewModelProvider(this).get(MainActivityData.class);
        initUI();
        if(data.getTraks() == null || data.getTraks().isEmpty()) initPresenter();
        else showTracks(data.getTraks());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_delete_all) {
            presenter.deleteAllTracks();
            data.restart();
            setNumberRecords();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initUI() {
        initSwipeLayout();
        initAdapter();
        initScrollListener();
        setNumberRecords();
        showLoading();
    }

    private void initSwipeLayout(){
        mWaveSwipeRefreshLayout.setColorSchemeColors(Color.WHITE, Color.WHITE);
        mWaveSwipeRefreshLayout.setWaveColor(Color.rgb(10, 182,234));
        mWaveSwipeRefreshLayout.setOnRefreshListener(this::loadNextTracks);
    }

    private void initAdapter() {
        trackAdapter = new TrackAdapter();
        trackAdapter.setListener(this);
        recyclerViewTracks.setAdapter(trackAdapter);
    }

    private void initScrollListener() {
        recyclerViewTracks.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if(!data.isLoading()) {
                    if(linearLayoutManager != null && data.getTraks().size() > 8 &&
                            linearLayoutManager.findLastCompletelyVisibleItemPosition() == data.getTraks().size() - 1) {
                        showLoading();
                        data.setLoading(true);
                        loadNextTracks();
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void initPresenter() {
        presenter.setPage(data.getPage());
        presenter.onAttach();
    }


    private void setNumberRecords(){
        numberRecords.setText(String.valueOf(data.getNumResults()));
    }


    private void loadNextTracks() {
        recyclerViewTracks.setVisibility(View.VISIBLE);
        presenter.setPage(data.getPage());
        presenter.onAttach();
    }

    @Override
    protected void initInject(){
        App app = (App) this.getApplication();
        app.getMainComponent().inject(this);
        app.getMainComponent().inject(presenter);
    }

    @NonNull
    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    public void showTracks(List<Track> traks) {
        runOnUiThread(() -> {
            mWaveSwipeRefreshLayout.setVisibility(View.VISIBLE);
            layoutNoRecords.setVisibility(View.GONE);
            trackAdapter.submitList(traks);
            data.setTraks(traks);
            data.setPage(traks.size() > data.getNumResults() ? data.getPage() + 1: data.getPage());
            data.setNumResults(traks.size());
            setNumberRecords();
            hideLoading();
            data.setLoading(false);
        });
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(this, "Server error!!", Toast.LENGTH_SHORT).show();
        hideLoading();
    }

    @Override
    public void showThereIsNoTracks() {
        runOnUiThread(() -> {
            mWaveSwipeRefreshLayout.setVisibility(View.GONE);
            layoutNoRecords.setVisibility(View.VISIBLE);
            hideLoading();
        });
    }

    @Override
    public void showLoading() {
        mWaveSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        new Handler().postDelayed(() -> {
            mWaveSwipeRefreshLayout.setRefreshing(false);
            progressBarSearch.setVisibility(View.GONE);
        }, 1000);
    }

    @Override
    public void onTrackClicked(Track track) {
        hideLoading();
        Navegador.mainToDetails(this, track);
    }

    @OnClick(R.id.btn_search_tracks)
    void search() {
        progressBarSearch.setVisibility(View.VISIBLE);
        loadNextTracks();
    }
}
