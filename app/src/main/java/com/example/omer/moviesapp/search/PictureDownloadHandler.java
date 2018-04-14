package com.example.omer.moviesapp.search;

import android.content.Context;

import com.example.omer.moviesapp.GetPictureCallBack;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;


public class PictureDownloadHandler {

    String posterPath;
    Context context;
    String BASE_URL_FOR_PICTURES = "https://image.tmdb.org/t/p/w500";

    public PictureDownloadHandler(String posterPath, Context context) {
        this.posterPath = posterPath;
        this.context = context;
    }

    public void GetImageFromURL(GetPictureCallBack getPictureCALLBACK) {
        final RequestCreator load = Picasso.with(context).load(BASE_URL_FOR_PICTURES + posterPath);
        getPictureCALLBACK.onPictureLoaded(load);
    }
}
