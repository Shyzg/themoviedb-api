package com.mobileapplicationdevelopment.themoviedb.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mobileapplicationdevelopment.themoviedb.Model.Movies;
import com.mobileapplicationdevelopment.themoviedb.Model.NowPlayingModel;
import com.mobileapplicationdevelopment.themoviedb.Repositories.MovieRepository;

public class MovieViewModel extends AndroidViewModel {
    private final MovieRepository movieRepository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance();
    }

    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();

    public void getMovieById(String movieId) {
        resultGetMovieById = movieRepository.getMovieData(movieId);
    }

    public LiveData<Movies> getResultGetMovieById() {
        return resultGetMovieById;
    }

    private MutableLiveData<NowPlayingModel> resultGetNowPlaying = new MutableLiveData<>();

    public void getNowPlaying() {
        resultGetNowPlaying = movieRepository.getNowPlayingData();
    }

    public LiveData<NowPlayingModel> getResultGetNowPlaying() {
        return resultGetNowPlaying;
    }
}