package com.example.omer.moviesapp.search;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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

    private RecyclerView recyclerView;
    private List<Movie> listOfMovies;
    private Context context;
    URL url;
    private int mExpandedPosition = -1;


    SearchedMoviesAdapter(Context context, List<Movie> list, RecyclerView recyclerView) {
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
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PictureDownloadHandler pictureDownloadHandler = new PictureDownloadHandler(listOfMovies.get(position).getPosterPath(), context);
        pictureDownloadHandler.GetImageFromURL(new GetPictureCallBack() {
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
        holder.toggleButton.setChecked(false);
        holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.transperent_star));
        holder.toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.yellow_star));
                else
                    holder.toggleButton.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.transperent_star));
            }
        });

        final boolean isExpanded = position == mExpandedPosition;
        holder.view.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        holder.titleText.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
        holder.itemView.setActivated(isExpanded);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExpandedPosition = isExpanded ? -1 : holder.getAdapterPosition();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    TransitionManager.beginDelayedTransition(recyclerView);
                }
                notifyItemChanged(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfMovies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView titleText;
        TextView titleTextOfInformation;
        TextView movie_information;
        ImageView moviePicutre;
        public View view;
        private ToggleButton toggleButton;

        ViewHolder(View itemView) {
            super(itemView);
            titleText = (TextView) itemView.findViewById(R.id.movie_title);
            movie_information = (TextView) itemView.findViewById(R.id.movie_information);
            moviePicutre = (ImageView) itemView.findViewById(R.id.movie_picture);
            view = (View) itemView.findViewById(R.id.cheky_check);
            titleTextOfInformation = (TextView) itemView.findViewById(R.id.title_text_movie_information);
            toggleButton = itemView.findViewById(R.id.favorite_button);
        }
    }
}
