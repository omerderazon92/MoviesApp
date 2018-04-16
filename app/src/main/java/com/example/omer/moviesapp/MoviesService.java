package com.example.omer.moviesapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Omer on 10/04/2018.
 */

public interface MoviesService {

    @GET("search/movie")
    Call<MovieResponse> getListOfMovies(@Query("api_key") String apiKey, @Query("query") String searchParameter);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("tv/movie_id/images")
    Call<MovieResponse> getMoreImages(@Query("api_key") String apiKey, @Query("movie_id") Integer movieID);


}
