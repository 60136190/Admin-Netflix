package com.example.adminnetflix.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminnetflix.R;
import com.example.adminnetflix.activities.DetailVideoActivity;
import com.example.adminnetflix.models.response.SeriesFilm;
import com.example.adminnetflix.utils.Contants;
import com.example.adminnetflix.utils.StoreUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SeriesFilmAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context mContext;
    List<SeriesFilm> mDetailFilms;

    public SeriesFilmAdapter(Context mContext, List<SeriesFilm> mDetailFilms) {
        this.mContext = mContext;
        this.mDetailFilms = mDetailFilms;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_series_film, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        SeriesFilm detailFilm = mDetailFilms.get(position);
        String imgFilm = detailFilm.getUrlImage();
        String urlVideo = detailFilm.getUrlVideo();
        int episode = detailFilm.getEpisode();
        ((ItemViewHolder) holder).tvSeries.setText(String.valueOf("Episode "+detailFilm.getEpisode()));
        Picasso.with(mContext)
                .load(imgFilm).fit().centerInside().into(((ItemViewHolder) holder).imgFilm);

        ((ItemViewHolder) holder).imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, DetailVideoActivity.class);
                i.putExtra("video", urlVideo);
                i.putExtra("episode", episode);
                mContext.startActivity(i);
            }
        });
        String canwatch = "true";
//        if (StoreUtil.get(mContext, Contants.canWatch).equals(canwatch)) {
//            ((ItemViewHolder) holder).imgPlay.setVisibility(View.VISIBLE);
//            Log.i("oknhaa", "can watch");
//        } else {
//            ((ItemViewHolder) holder).imgPlay.setVisibility(View.INVISIBLE);
//            Log.i("oknhaa", "can't watch");
//        }

    }


    @Override
    public int getItemCount() {
        if (mDetailFilms != null) {
            return mDetailFilms.size();
        }
        return 0;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imgFilm, imgPlay;
        TextView tvSeries;

        public ItemViewHolder(View itemView) {
            super(itemView);
            imgFilm = itemView.findViewById(R.id.img_film);
            tvSeries = itemView.findViewById(R.id.tv_series_film);
            imgPlay = itemView.findViewById(R.id.img_play);
        }
    }

}
