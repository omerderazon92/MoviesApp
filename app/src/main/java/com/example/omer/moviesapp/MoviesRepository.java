package com.example.omer.moviesapp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Omer on 10/04/2018.
 */

class MoviesRepository {

    private ServerCall serverCall;
    private static final String API_KEY = "47670a230cbfe18bf88b7f57d26ae7c4";

    MoviesRepository(ServerCall serverCall) {
        this.serverCall = serverCall;
    }


    void getListOfMovies(String searchParameter, final GetMoviesCallback getMoviesCallback) {
        serverCall.getListOfMovies(API_KEY, searchParameter).enqueue(new Callback<MovieResponse>() {
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
