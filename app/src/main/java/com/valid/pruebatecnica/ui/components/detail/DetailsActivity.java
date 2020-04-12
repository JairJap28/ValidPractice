package com.valid.pruebatecnica.ui.components.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;
import com.valid.pruebatecnica.App;
import com.valid.pruebatecnica.R;
import com.valid.pruebatecnica.data.entity.Artist;
import com.valid.pruebatecnica.data.entity.Track;
import com.valid.pruebatecnica.ui.base.BaseActivity;
import com.valid.pruebatecnica.ui.base.Navegador;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity<DetailsPresenter> implements DetailsView  {

    public static final String EXTRA_TRACK = "com.valid.pruebatecnica.EXTRA_TRACK";

    @BindView(R.id.image_view_track) ImageView imageViewTrack;
    @BindView(R.id.text_view_title_detail) TextView titleTrack;
    @BindView(R.id.text_view_listeners) TextView textViewListeners;
    @BindView(R.id.text_view_duration) TextView texViewDuration;
    @BindView(R.id.text_view_rank_details) TextView textViewRank;
    @BindView(R.id.text_view_mbid_track) TextView textViewMbIdTrack;
    @BindView(R.id.text_view_name_artist) TextView textViewNameArtist;
    @BindView(R.id.text_view_mbid_artist) TextView textViewMbIdArtist;
    @BindView(R.id.text_view_number_songs) TextView textViewNumberSongs;

    private Track track;
    private Artist artist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_activity);
        ButterKnife.bind(this);
        initUI();
    }

    @OnClick(R.id.btn_go_home)
    void goHome(){
        finish();
    }

    @OnClick(R.id.btn_browser_track)
    void browserTrack(){
        if(track != null && !track.getUrl().isEmpty()) Navegador.toBrowser(this, track.getUrl());
        else Toast.makeText(this, "No es posible navegar a esa url", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_browser_artist)
    void browserArtist(){
        if(artist != null && !artist.getUrl().isEmpty()) Navegador.toBrowser(this, artist.getUrl());
        else Toast.makeText(this, "No es posible navegar a esa url", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initInject() {
        App app = (App) this.getApplication();
        app.getMainComponent().inject(this);
        app.getMainComponent().inject(presenter);
    }

    @NonNull
    @Override
    protected DetailsPresenter createPresenter() {
        return new DetailsPresenter(this);
    }


    void initUI() {
        Intent intent = getIntent();
        track = intent.getParcelableExtra(EXTRA_TRACK);
        if(track != null) {
            setImageViewTrack(track.getImage());
            setTitleTrack(track.getName());
            setListeners(track.getListeners());
            setDuration(track.getDuration());
            setRank(track.getRank());
            setMbIdTrack(track.getMbid());
            initPresenter();
        }
    }

    private void initPresenter() {
        presenter.setMbIdTrack(track.getMbid());
        presenter.onAttach();
    }

    void setImageViewTrack(String url) {
        if(!url.isEmpty()) {
            Picasso.get()
                    .load(url)
                    .into(imageViewTrack);
        }
    }

    void setTitleTrack(String title){
        titleTrack.setText(title);
    }

    void setListeners(String listeners){
        textViewListeners.setText(listeners);
    }

    void setDuration(String duration) {
        texViewDuration.setText(duration);
    }

    void setRank(String rank) {
        textViewRank.setText(rank);
    }

    void setMbIdTrack(String mbid){
        textViewMbIdTrack.setText(mbid);
    }

    void setNameArtist(String nameArtist) {
        textViewNameArtist.setText(nameArtist);
    }

    void setMbIdArtist(String mbid){
        textViewMbIdArtist.setText(mbid);
    }

    void setNumberSongs(String numberSongs) {
        textViewNumberSongs.setText(numberSongs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showNumberTracks(int numberTracks) {
        setNumberSongs(String.valueOf(numberTracks));
    }

    @Override
    public void showArtist(Artist artist) {
        this.artist = artist;
        setNameArtist(artist.getName());
        setMbIdArtist(artist.getMbid());
    }
}
