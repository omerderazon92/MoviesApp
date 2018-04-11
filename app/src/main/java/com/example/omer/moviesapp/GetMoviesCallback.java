package com.example.omer.moviesapp;

import java.util.List;

/**
 * Created by Omer on 10/04/2018.
 */

public interface GetMoviesCallback {


    void onMoviesReceived(List<Movie> movies);

    void onDataNotAvailable();

}
