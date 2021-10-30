package com.mobileapplicationdevelopment.themoviedb.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.mobileapplicationdevelopment.themoviedb.Model.CreditsModel;
import com.mobileapplicationdevelopment.themoviedb.Model.MoviesModel;
import com.mobileapplicationdevelopment.themoviedb.Model.NowPlayingModel;
import com.mobileapplicationdevelopment.themoviedb.Model.UpcomingModel;
import com.mobileapplicationdevelopment.themoviedb.Repositories.MovieRepository;

public class MovieViewModel extends AndroidViewModel {
    private final MovieRepository movieRepository;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        movieRepository = MovieRepository.getInstance();
    }

    private MutableLiveData<MoviesModel> resultGetMovieById = new MutableLiveData<>();

    public void getMovieById(String movieId) {
        resultGetMovieById = movieRepository.getMovieData(movieId);
    }

    public LiveData<MoviesModel> getResultGetMovieById() {
        return resultGetMovieById;
    }

    private MutableLiveData<CreditsModel> resultGetCredits = new MutableLiveData<>();

    public void getCredits(String movieId) {
        resultGetCredits = movieRepository.getCreditsData(movieId);
    }

    public LiveData<CreditsModel> getResultGetCredits() {
        return resultGetCredits;
    }

    private MutableLiveData<NowPlayingModel> resultGetNowPlaying = new MutableLiveData<>();

    public void getNowPlaying() {
        resultGetNowPlaying = movieRepository.getNowPlayingData();
    }

    public LiveData<NowPlayingModel> getResultGetNowPlaying() {
        return resultGetNowPlaying;
    }

    private MutableLiveData<UpcomingModel> resultGetUpComing = new MutableLiveData<>();

    public void getUpComing() {
        resultGetUpComing = movieRepository.getUpComingData();
    }

    public LiveData<UpcomingModel> getResultGetUpComing() {
        return resultGetUpComing;
    }
}