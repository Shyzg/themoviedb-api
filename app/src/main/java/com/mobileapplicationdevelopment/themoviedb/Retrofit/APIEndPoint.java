package com.mobileapplicationdevelopment.themoviedb.Retrofit;

import com.mobileapplicationdevelopment.themoviedb.Model.Movies;
import com.mobileapplicationdevelopment.themoviedb.Model.NowPlayingModel;
import com.mobileapplicationdevelopment.themoviedb.Model.UpComing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIEndPoint {
    @GET("movie/{movie_id}")
    Call<Movies> getMovieById(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );

    @GET("movie/now_playing")
    Call<NowPlayingModel> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("movie/upcoming")
    Call<UpComing> getUpComing(
            @Query("api_key") String apiKey
    );
}