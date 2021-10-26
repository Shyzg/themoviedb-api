package com.mobileapplicationdevelopment.themoviedb.View.Activities;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.mobileapplicationdevelopment.themoviedb.Model.Movies;
import com.mobileapplicationdevelopment.themoviedb.R;
import com.mobileapplicationdevelopment.themoviedb.ViewModel.MovieViewModel;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private MovieViewModel movieViewModel;
    private TextView tvTitle;
    private TextInputLayout tilMovieId;
    private ImageView ivPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = new ViewModelProvider(MainActivity.this).get(MovieViewModel.class);
        tvTitle = findViewById(R.id.tv_title_main);
        tilMovieId = findViewById(R.id.til_movieid_main);
        ivPoster = findViewById(R.id.iv_poster_main);
        Button buttonAPI = findViewById(R.id.button_api_main);
        buttonAPI.setOnClickListener(view -> {
            String movieId = Objects.requireNonNull(tilMovieId.getEditText()).getText().toString().trim();
            if (movieId.isEmpty()) {
                tilMovieId.setError("Tidak Boleh Kosong!");
            } else {
                tilMovieId.setError(null);
                movieViewModel.getMovieById(movieId);
                movieViewModel.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
            }
        });
    }

    private final Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if (movies == null) {
                tvTitle.setText(R.string.test);
            } else {
                String title = movies.getTitle();
                String imagePath = movies.getPoster_path().toString();
                String fullPath = "https://image.tmdb.org/t/p/original/" + imagePath;
                Glide.with(MainActivity.this).load(fullPath).into(ivPoster);
                tvTitle.setText(title);
            }
        }
    };
}