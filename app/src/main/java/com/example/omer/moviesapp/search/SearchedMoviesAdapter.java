package com.example.omer.moviesapp.search;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omer.moviesapp.GetPictureCallBack;
import com.example.omer.moviesapp.Movie;
import com.example.omer.moviesapp.R;
import com.squareup.picasso.RequestCreator;

import java.net.URL;
import java.util.List;


/**
 * Created by Omer on 10/04/2018.
 */

public class SearchedMoviesAdapter extends RecyclerView.Adapter<SearchedMoviesAdapter.ViewHolder> {

    RecyclerView recyclerView;
    List<Movie> listOfMovies;
    Context context;
    URL url;
    int mExpandedPosition = -1;


    public SearchedMoviesAdapter(Context context, List<Movie> list, RecyclerView recyclerView) {
        listOfMovies = list;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_of_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        PictureDownloadHandler pictureDownloadHendler = new PictureDownloadHandler(listOfMovies.get(position).getPosterPath(), context);
        pictureDownloadHendler.GetImageFromURL(new GetPictureCallBack() {
            @Override
            public void onPictureLoaded(RequestCreator requestCreator) {
                requestCreator.into(holder.moviePicutre);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });

        holder.titleText.setText(listOfMovies.get(position).getTitle());
        holder.movie_information.setText(listOfMovies.get(position).getOverview());
        holder.titleTextOfInformation.setText(listOfMovies.get(position).getTitle());

        final boolean isExpanded = position == mExpandedPosition;
        holder.view.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.titleText.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : position;
                TransitionManager.beginDelayedTransition(recyclerView);
                notifyItemChanged(position);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listOfMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titleText;
        public TextView titleTextOfInformation;
        public TextView movie_information;
        public ImageView moviePicutre;
        public View view;

        public ViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.movie_title);
            movie_information = (TextView) itemView.findViewById(R.id.movie_information);
            moviePicutre = (ImageView) itemView.findViewById(R.id.movie_picture);
            view = (View) itemView.findViewById(R.id.cheky_check);
            titleTextOfInformation = (TextView) itemView.findViewById(R.id.title_text_movie_information);
        }
    }
}
