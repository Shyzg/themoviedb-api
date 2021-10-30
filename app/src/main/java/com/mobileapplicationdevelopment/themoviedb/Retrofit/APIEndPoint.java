package com.mobileapplicationdevelopment.themoviedb.Retrofit;

import com.mobileapplicationdevelopment.themoviedb.Model.CreditsModel;
import com.mobileapplicationdevelopment.themoviedb.Model.MoviesModel;
import com.mobileapplicationdevelopment.themoviedb.Model.NowPlayingModel;
import com.mobileapplicationdevelopment.themoviedb.Model.UpcomingModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIEndPoint {
    @GET("movie/{movie_id}")
    Call<MoviesModel> getMovieById(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );

    @GET("movie/{movie_id}/credits")
    Call<CreditsModel> getCredits(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );

    @GET("movie/now_playing")
    Call<NowPlayingModel> getNowPlaying(
            @Query("api_key") String apiKey
    );

    @GET("movie/upcoming")
    Call<UpcomingModel> getUpComing(
            @Query("api_key") String apiKey
    );
}