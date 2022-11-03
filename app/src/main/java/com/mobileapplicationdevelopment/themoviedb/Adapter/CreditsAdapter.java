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
import com.mobileapplicationdevelopment.themoviedb.Model.CreditsModel;
import com.mobileapplicationdevelopment.themoviedb.R;

import java.util.List;

public class CreditsAdapter extends RecyclerView.Adapter<CreditsAdapter.CardViewViewHolder> {
    private final Context context;
    private List<CreditsModel.Cast> castList;

    private List<CreditsModel.Cast> getCreditsList() {
        return castList;
    }

    public void setCreditsList(List<CreditsModel.Cast> castList) {
        this.castList = castList;
    }

    public CreditsAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_credits, parent, false);
        return new CreditsAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        final CreditsModel.Cast cast = getCreditsList().get(position);
        holder.tv_name.setText(cast.getCharacter());
        holder.tv_original_name.setText(cast.getOriginal_name());
        Glide.with(context).load(Const.IMG_URL + cast.getProfile_path()).into(holder.iv_profile);
    }

    @Override
    public int getItemCount() {
        return getCreditsList().size();
    }

    public static class CardViewViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_profile;
        TextView tv_name, tv_original_name;
        CardView cv_cast;

        public CardViewViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_profile = itemView.findViewById(R.id.iv_cast_card_credits);
            tv_name = itemView.findViewById(R.id.tv_name_card_credits);
            tv_original_name = itemView.findViewById(R.id.tv_original_name_card_credits);
            cv_cast = itemView.findViewById(R.id.cv_card_credits);
        }
    }
}
