package com.valid.pruebatecnica.ui.components.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.valid.pruebatecnica.R;
import com.valid.pruebatecnica.data.entity.Track;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrackAdapter extends ListAdapter<Track, TrackAdapter.TrackViewHolder> {

    public interface TrackListener {
        void onTrackClicked(Track track);
    }

    private TrackListener listener;

    public void setListener(TrackListener listener) {
        this.listener = listener;
    }

    public TrackAdapter() {
        super(DIFF_CALL);
    }

    private static final DiffUtil.ItemCallback<Track> DIFF_CALL = new DiffUtil.ItemCallback<Track>() {
        @Override
        public boolean areItemsTheSame(@NonNull Track oldItem, @NonNull Track newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Track oldItem, @NonNull Track newItem) {
            return oldItem.getId() == newItem.getId() &&
                    oldItem.getMbid().equals(newItem.getMbid()) &&
                    oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getDuration().equals(newItem.getDuration()) &&
                    oldItem.getListeners().equals(newItem.getListeners()) &&
                    oldItem.getImage().equals(newItem.getImage()) &&
                    oldItem.getUrl().equals(newItem.getUrl());
        }
    };

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_track , parent, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder holder, int position) {
        holder.bind(position);
    }

    class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.image) AppCompatImageView imageView;
        @BindView(R.id. text_view_title) TextView title;
        @BindView(R.id.text_view_listeners) TextView listeners;
        @BindView(R.id.text_view_duration) TextView duration;
        @BindView(R.id.text_view_rank) TextView rank;

        private TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            Track track = getItem(position);
            setClickListener(track);
            setImageView(track.getImage());
            setTitle(track.getName());
            setListeners(track.getListeners());
            setDuration(track.getDuration());
            setRank(track.getRank());
        }

        private void setImageView(String url){
            if(!url.isEmpty()) {
                Picasso.get()
                        .load(url)
                        .into(imageView);
            }
        }

        private void setTitle(String title) {
            this.title.setText(title);
        }

        private void setListeners(String listeners) {
            this.listeners.setText(listeners);
        }

        private void setDuration(String duration) { this.duration.setText(duration); }

        private void setRank(String rank) { this.rank.setText(rank); }

        void setClickListener(Track track) {
            itemView.setTag(track);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onTrackClicked((Track) view.getTag());
        }
    }
}
