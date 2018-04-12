package com.example.omer.moviesapp.top_rated_movie;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omer.moviesapp.GetMoviesCallback;
import com.example.omer.moviesapp.Movie;
import com.example.omer.moviesapp.MoviesRepository;
import com.example.omer.moviesapp.R;
import com.example.omer.moviesapp.ServerCall;
import com.example.omer.moviesapp.search.ListOfMoviesAcitivity;
import com.example.omer.moviesapp.search.MoviesAdapter;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopRatedFragment extends Fragment {

    public static final String MOVIE_DATABASE_BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = "47670a230cbfe18bf88b7f57d26ae7c4";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public TopRatedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_top_rated, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_DATABASE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerCall serverCall = retrofit.create(ServerCall.class);

        final MoviesRepository moviesRepository = new MoviesRepository(serverCall);
        moviesRepository.getTopRatedMovie(new GetMoviesCallback() {
            @Override
            public void onMoviesReceived(List<Movie> movies) {
                mRecyclerView = view.findViewById(R.id.list_of_top_rated_movies_recycler_view);
                mLayoutManager = new LinearLayoutManager(TopRatedFragment.this.getContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setHasFixedSize(true);
                mAdapter = new MoviesAdapter(TopRatedFragment.this.getContext(), movies);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });


    }
}
