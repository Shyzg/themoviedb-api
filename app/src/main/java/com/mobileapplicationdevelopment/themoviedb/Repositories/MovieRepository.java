package com.mobileapplicationdevelopment.themoviedb.Repositories;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.mobileapplicationdevelopment.themoviedb.Helper.Const;
import com.mobileapplicationdevelopment.themoviedb.Model.Movies;
import com.mobileapplicationdevelopment.themoviedb.Model.NowPlayingModel;
import com.mobileapplicationdevelopment.themoviedb.Retrofit.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private static MovieRepository movieRepository;

    private MovieRepository() {
    }

    public static MovieRepository getInstance() {
        if (movieRepository == null)
            return movieRepository = new MovieRepository();

        return movieRepository;
    }

    public MutableLiveData<Movies> getMovieData(String movieId) {
        final MutableLiveData<Movies> result = new MutableLiveData<>();
        APIService.endPoint().getMovieById(movieId, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(@NonNull Call<Movies> call, @NonNull Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Movies> call, @NonNull Throwable t) {

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
}