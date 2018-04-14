package com.example.omer.moviesapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Omer on 14/04/2018.
 */

public class MoviesServiceProvider {

    public static final String MOVIE_DATABASE_BASE_URL = "https://api.themoviedb.org/3/";
    private static MoviesService moviesService;

    public static MoviesService getMoviesService() {
        if (moviesService == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(MOVIE_DATABASE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            moviesService = retrofit.create(MoviesService.class);
        }
        return moviesService;
    }
}
