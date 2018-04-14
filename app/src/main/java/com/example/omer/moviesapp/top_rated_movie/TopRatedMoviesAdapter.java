package com.example.omer.moviesapp.top_rated_movie;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omer.moviesapp.GetPictureCallBack;
import com.example.omer.moviesapp.Movie;
import com.example.omer.moviesapp.R;
import com.example.omer.moviesapp.search.PictureDownloadHandler;
import com.squareup.picasso.RequestCreator;

import java.util.List;

/**
 * Created by Omer on 12/04/2018.
 */

public class TopRatedMoviesAdapter extends RecyclerView.Adapter<TopRatedMoviesAdapter.MovieViewHoler> {
    List<Movie> listOfMovies;
    Context context;
    Fragment newFragment = new InfoOfMovieFragment();

    public TopRatedMoviesAdapter(List<Movie> list, Context context) {
        this.listOfMovies = list;
        this.context = context;
    }

    @Override
    public MovieViewHoler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.top_rated_card_view, parent, false);
        return new MovieViewHoler(view);
    }

    @Override
    public void onBindViewHolder(final MovieViewHoler holder, final int position) {
        PictureDownloadHandler pictureDownloadHendler = new PictureDownloadHandler(listOfMovies.get(position).getPosterPath(), context);
        pictureDownloadHendler.GetImageFromURL(new GetPictureCallBack() {
            @Override
            public void onPictureLoaded(RequestCreator requestCreator) {
                requestCreator.into(holder.moviePicture);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

       holder.nameOfMovie.setText(listOfMovies.get(position).getTitle());


    }

    @Override
    public int getItemCount() {
        return listOfMovies.size();
    }

    public class MovieViewHoler extends RecyclerView.ViewHolder {

        ImageView moviePicture;
        TextView nameOfMovie;

        public MovieViewHoler(View itemView) {
            super(itemView);
            moviePicture = itemView.findViewById(R.id.movie_top_rated);
            nameOfMovie = itemView.findViewById(R.id.name_of_movie);
        }
    }
}
