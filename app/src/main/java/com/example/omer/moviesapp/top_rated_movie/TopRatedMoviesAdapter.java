package com.example.omer.moviesapp.top_rated_movie;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.omer.moviesapp.GetPictureCALLBACK;
import com.example.omer.moviesapp.Movie;
import com.example.omer.moviesapp.R;
import com.example.omer.moviesapp.search.PictureDownloadHendler;
import com.example.omer.moviesapp.search.SearchedMoviesAdapter;
import com.squareup.picasso.RequestCreator;

import java.util.List;

/**
 * Created by Omer on 12/04/2018.
 */

public class TopRatedMoviesAdapter extends RecyclerView.Adapter<TopRatedMoviesAdapter.ViewHolder> {
    List<Movie> listOfMovies;
    Context context;

    public TopRatedMoviesAdapter(List<Movie> list, Context context) {
        this.listOfMovies = list;
        this.context = context;
    }

    @Override
    public TopRatedMoviesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_rated_card_view, parent, false);
        return new TopRatedMoviesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TopRatedMoviesAdapter.ViewHolder holder, int position) {

        PictureDownloadHendler pictureDownloadHendler = new PictureDownloadHendler(listOfMovies.get(position).getPosterPath(), context);
        pictureDownloadHendler.GetImageFromURL(new GetPictureCALLBACK() {
            @Override
            public void onMoviesReceived(RequestCreator requestCreator) {
                requestCreator.into(holder.moviePicture);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

    }

    @Override
    public int getItemCount() {
        return listOfMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView moviePicture;

        public ViewHolder(View itemView) {
            super(itemView);
            moviePicture = itemView.findViewById(R.id.movie_top_rated);
        }
    }
}
