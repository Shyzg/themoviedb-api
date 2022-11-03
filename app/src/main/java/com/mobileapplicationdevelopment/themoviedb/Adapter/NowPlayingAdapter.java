package com.mobileapplicationdevelopment.themoviedb.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.mobileapplicationdevelopment.themoviedb.Helper.Const;
import com.mobileapplicationdevelopment.themoviedb.Model.NowPlayingModel;
import com.mobileapplicationdevelopment.themoviedb.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.CardViewViewHolder> {
    private final Context context;
    private List<NowPlayingModel.Results> listNowPlaying;

    private List<NowPlayingModel.Results> getListNowPlaying() {
        return listNowPlaying;
    }

    public void setListNowPlaying(List<NowPlayingModel.Results> listNowPlaying) {
        this.listNowPlaying = listNowPlaying;
    }

    public NowPlayingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override

    public NowPlayingAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_now_playing, parent, false);
        return new NowPlayingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final NowPlayingModel.Results results = getListNowPlaying().get(position);
//        final MoviesModel.
        StringBuilder genre = new StringBuilder();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Glide.with(context).load(Const.IMG_URL + results.getPoster_path()).into(holder.ivPoster);

        for (int i = 0; i < results.getGenre_ids().size(); i++) {
            if (i == results.getGenre_ids().size() - 1) {
                genre.append(results.getGenre_ids().get(i));
            } else {
                genre.append(results.getGenre_ids().get(i)).append(", ");
            }
        }

        holder.tvTitle.setText(results.getTitle());
        holder.tvGenre.setText(genre);
        holder.tvRating.setText(String.valueOf(results.getVote_average()));

        try {
            String release = formatter.format(Objects.requireNonNull(dateFormat.parse(results.getRelease_date())));
            holder.tvReleaseDate.setText(release);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return getListNowPlaying().size();
    }

    public static class CardViewViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView ivPoster;
        TextView tvTitle, tvGenre, tvRating, tvReleaseDate;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv_card_now_playing);
            ivPoster = itemView.findViewById(R.id.iv_card_now_playing);
            tvTitle = itemView.findViewById(R.id.tv_title_card_now_playing);
            tvGenre = itemView.findViewById(R.id.tv_genre_card_now_playing);
            tvRating = itemView.findViewById(R.id.tv_rating_card_now_playing);
            tvReleaseDate = itemView.findViewById(R.id.tv_releasedate_card_now_playing);
        }
    }
}