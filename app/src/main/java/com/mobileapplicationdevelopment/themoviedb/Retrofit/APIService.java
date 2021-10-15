package com.mobileapplicationdevelopment.themoviedb.Retrofit;

import com.mobileapplicationdevelopment.themoviedb.Helper.Const;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIService {
    public static APIEndPoint endPoint() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Const.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(APIEndPoint.class);
    }
}