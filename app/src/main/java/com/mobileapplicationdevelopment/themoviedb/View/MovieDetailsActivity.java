package com.mobileapplicationdevelopment.themoviedb.View;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.mobileapplicationdevelopment.themoviedb.Helper.Const;
import com.mobileapplicationdevelopment.themoviedb.Model.Movies;
import com.mobileapplicationdevelopment.themoviedb.ViewModel.MovieViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import themoviedb.R;

public class MovieDetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        Intent intent = getIntent();

        String movie_id = intent.getStringExtra("movie_id");
        String title = intent.getStringExtra("title");
        String rating = intent.getStringExtra("rating");
        String releasedate = intent.getStringExtra("releasedate");

        MovieViewModel movieViewModel = new ViewModelProvider(MovieDetailsActivity.this).get(MovieViewModel.class);
        movieViewModel.getMovieById(movie_id);
        movieViewModel.getResultGetMovieById().observe(MovieDetailsActivity.this, showMoviesResult);

        TextView tv_title = findViewById(R.id.tv_title_movie_details);
        TextView tv_rating = findViewById(R.id.tv_rating_movie_details);
        tv_title.setText(String.format("%s (%s)", title, releasedate));
        tv_rating.setText(rating);
    }

    private final Observer<Movies> showMoviesResult = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            ImageView iv_backdrop = findViewById(R.id.iv_backdop_movie_details);
            TextView tv_genre = findViewById(R.id.tv_genre_movie_details);
            TextView tv_duration = findViewById(R.id.tv_duration_movie_details);
            TextView tv_synopsis = findViewById(R.id.tv_synopsis_movie_details);

            SimpleDateFormat timeFormatter = new SimpleDateFormat("h'h' m'm'");
            SimpleDateFormat timeFormat = new SimpleDateFormat("m");

            StringBuilder genre = new StringBuilder();
            for (int i = 0; i < movies.getGenres().size(); i++) {
                if (i == movies.getGenres().size() - 1) {
                    genre.append(movies.getGenres().get(i).getName());
                } else {
                    genre.append(movies.getGenres().get(i).getName()).append("\n");
                }
            }

            tv_genre.setText(genre);
            try {
                tv_duration.setText(timeFormatter.format(timeFormat.parse(String.valueOf(movies.getRuntime()))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Glide.with(MovieDetailsActivity.this).load(Const.IMG_URL + movies.getPoster_path()).into(iv_backdrop);
            tv_synopsis.setText(movies.getOverview());
        }
    };

    @Override
    public void onBackPressed() {
        finish();
    }
}