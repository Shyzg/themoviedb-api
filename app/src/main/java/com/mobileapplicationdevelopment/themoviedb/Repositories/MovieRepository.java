package com.mobileapplicationdevelopment.themoviedb.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mobileapplicationdevelopment.themoviedb.Helper.Const;
import com.mobileapplicationdevelopment.themoviedb.Model.CreditsModel;
import com.mobileapplicationdevelopment.themoviedb.Model.MoviesModel;
import com.mobileapplicationdevelopment.themoviedb.Model.NowPlayingModel;
import com.mobileapplicationdevelopment.themoviedb.Model.UpcomingModel;
import com.mobileapplicationdevelopment.themoviedb.Retrofit.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository movieRepository;

    public static MovieRepository getInstance() {
        if (movieRepository == null)
            return movieRepository = new MovieRepository();

        return movieRepository;
    }

    public MutableLiveData<MoviesModel> getMovieData(String movieId) {
        final MutableLiveData<MoviesModel> result = new MutableLiveData<>();
        APIService.endPoint().getMovieById(movieId, Const.API_KEY).enqueue(new Callback<MoviesModel>() {
            @Override
            public void onResponse(@NonNull Call<MoviesModel> call, @NonNull Response<MoviesModel> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<MoviesModel> call, @NonNull Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<CreditsModel> getCreditsData(String movieId) {
        final MutableLiveData<CreditsModel> result = new MutableLiveData<>();
        APIService.endPoint().getCredits(movieId, Const.API_KEY).enqueue(new Callback<CreditsModel>() {
            @Override
            public void onResponse(@NonNull Call<CreditsModel> call, @NonNull Response<CreditsModel> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<CreditsModel> call, @NonNull Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<NowPlayingModel> getNowPlayingData() {
        final MutableLiveData<NowPlayingModel> result = new MutableLiveData<>();
        APIService.endPoint().getNowPlaying(Const.API_KEY).enqueue(new Callback<NowPlayingModel>() {
            @Override
            public void onResponse(@NonNull Call<NowPlayingModel> call, @NonNull Response<NowPlayingModel> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<NowPlayingModel> call, @NonNull Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<UpcomingModel> getUpComingData() {
        final MutableLiveData<UpcomingModel> result = new MutableLiveData<>();
        APIService.endPoint().getUpComing(Const.API_KEY).enqueue(new Callback<UpcomingModel>() {
            @Override
            public void onResponse(@NonNull Call<UpcomingModel> call, @NonNull Response<UpcomingModel> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<UpcomingModel> call, @NonNull Throwable t) {

            }
        });

        return result;
    }
}