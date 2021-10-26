package com.mobileapplicationdevelopment.themoviedb.Adapter;

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
import com.mobileapplicationdevelopment.themoviedb.Model.UpComing;
import com.mobileapplicationdevelopment.themoviedb.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

public class UpComingAdapter extends RecyclerView.Adapter<UpComingAdapter.CardViewViewHolder> {
    private final Context context;
    private List<UpComing.Results> listUpComing;

    private List<UpComing.Results> getListUpComing() {
        return listUpComing;
    }

    public void setListUpComing(List<UpComing.Results> listUpComing) {
        this.listUpComing = listUpComing;
    }

    public UpComingAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override

    public UpComingAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_up_coming, parent, false);
        return new UpComingAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UpComingAdapter.CardViewViewHolder holder, int position) {
        final UpComing.Results results = getListUpComing().get(position);
        StringBuilder genre = new StringBuilder();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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
        return getListUpComing().size();
    }

    public static class CardViewViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView ivPoster;
        TextView tvTitle, tvGenre, tvRating, tvReleaseDate;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv_card_up_coming);
            ivPoster = itemView.findViewById(R.id.iv_card_up_coming);
            tvTitle = itemView.findViewById(R.id.tv_title_card_up_coming);
            tvGenre = itemView.findViewById(R.id.tv_genre_card_up_coming);
            tvRating = itemView.findViewById(R.id.tv_rating_card_up_coming);
            tvReleaseDate = itemView.findViewById(R.id.tv_releasedate_card_up_coming);
        }
    }
}
