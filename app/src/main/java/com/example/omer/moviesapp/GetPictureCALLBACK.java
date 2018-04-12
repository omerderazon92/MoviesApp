package com.example.omer.moviesapp;

import android.graphics.Bitmap;

import com.squareup.picasso.RequestCreator;

import java.util.List;

/**
 * Created by Omer on 12/04/2018.
 */

public interface GetPictureCALLBACK {

    void onMoviesReceived(RequestCreator requestCreator);

    void onDataNotAvailable();
}
