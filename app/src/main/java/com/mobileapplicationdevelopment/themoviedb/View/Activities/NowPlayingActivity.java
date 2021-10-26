package com.mobileapplicationdevelopment.themoviedb.View.Activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobileapplicationdevelopment.themoviedb.Adapter.NowPlayingAdapter;
import com.mobileapplicationdevelopment.themoviedb.Model.NowPlayingModel;
import com.mobileapplicationdevelopment.themoviedb.R;
import com.mobileapplicationdevelopment.themoviedb.ViewModel.MovieViewModel;

public class NowPlayingActivity extends AppCompatActivity {
    private RecyclerView rv_now_playing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        rv_now_playing = findViewById(R.id.rv_now_playing);
        MovieViewModel viewModel = new ViewModelProvider(NowPlayingActivity.this).get(MovieViewModel.class);
        viewModel.getNowPlaying();
        viewModel.getResultGetNowPlaying().observe(NowPlayingActivity.this, showNowPlaying);
    }

    private final Observer<NowPlayingModel> showNowPlaying = new Observer<NowPlayingModel>() {
        @Override
        public void onChanged(NowPlayingModel nowPlayingModel) {
            rv_now_playing.setLayoutManager(new LinearLayoutManager(NowPlayingActivity.this));
            NowPlayingAdapter adapter = new NowPlayingAdapter(NowPlayingActivity.this);
            adapter.setListNowPlaying(nowPlayingModel.getResults());
            rv_now_playing.setAdapter(adapter);
        }
    };
}