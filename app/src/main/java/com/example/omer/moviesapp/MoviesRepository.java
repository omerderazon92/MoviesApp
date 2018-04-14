package com.example.omer.moviesapp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Omer on 10/04/2018.
 */

public class MoviesRepository {

    private MoviesService moviesService;
    private static final String API_KEY = "47670a230cbfe18bf88b7f57d26ae7c4";

    public MoviesRepository(MoviesService moviesService) {
        this.moviesService = moviesService;
    }


    public void getListOfMovies(String searchParameter, final GetMoviesCallback getMoviesCallback) {
        moviesService.getListOfMovies(API_KEY, searchParameter).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    getMoviesCallback.onMoviesReceived(response.body().results);
                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                getMoviesCallback.onDataNotAvailable();
            }
        });

    }

    public void getTopRatedMovie(final GetMoviesCallback getMoviesCallback) {
        moviesService.getTopRatedMovies(API_KEY).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    getMoviesCallback.onMoviesReceived(response.body().results);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                getMoviesCallback.onDataNotAvailable();

            }
        });
    }
}
