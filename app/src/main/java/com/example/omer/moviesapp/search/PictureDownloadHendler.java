package com.example.omer.moviesapp.search;

import android.content.Context;

import com.example.omer.moviesapp.GetPictureCALLBACK;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;


class PictureDownloadHendler {

    String posterPath;
    Context context;
    String BASE_URL_FOR_PICTURES = "https://image.tmdb.org/t/p/w500";

    public PictureDownloadHendler(String posterPath, Context context) {
        this.posterPath = posterPath;
        this.context = context;
    }

    public void GetImageFromURL(GetPictureCALLBACK getPictureCALLBACK) {
        final RequestCreator load = Picasso.with(context).load(BASE_URL_FOR_PICTURES + posterPath);
        getPictureCALLBACK.onMoviesReceived(load);
    }
}
