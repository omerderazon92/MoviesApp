package com.example.omer.moviesapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Omer on 10/04/2018.
 */

public class MovieResponse {

    @SerializedName("page")
    int page;
    @SerializedName("total_results")
    int total_results;


    @SerializedName("total_pages")
    int total_pages;
    @SerializedName("results")
    List<Movie> results;


    public MovieResponse(int page, int total_results, int total_pages, List<Movie> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }
}
