package com.example.omer.moviesapp.top_rated_movie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.omer.moviesapp.GetMoviesCallback;
import com.example.omer.moviesapp.Movie;
import com.example.omer.moviesapp.MoviesRepository;
import com.example.omer.moviesapp.MoviesServiceProvider;
import com.example.omer.moviesapp.R;

import java.util.List;

public class TopRatedFragment extends Fragment {

    private static final String API_KEY = "47670a230cbfe18bf88b7f57d26ae7c4";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private MoviesRepository moviesRepository;

    public TopRatedFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviesRepository = new MoviesRepository(MoviesServiceProvider.getMoviesService());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_rated, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviesRepository.getTopRatedMovie(new GetMoviesCallback() {
            @Override
            public void onMoviesReceived(List<Movie> movies) {
                mRecyclerView = view.findViewById(R.id.list_of_top_rated_movies_recycler_view);
                ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(TopRatedFragment.this.getContext(), R.dimen.cardview_default_radius);
                mRecyclerView.addItemDecoration(itemDecoration);
                mLayoutManager = new GridLayoutManager(TopRatedFragment.this.getContext(), 2);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mRecyclerView.setHasFixedSize(true);
                mAdapter = new TopRatedMoviesAdapter(movies, TopRatedFragment.this.getContext());
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onDataNotAvailable() {
                Toast.makeText(TopRatedFragment.this.getContext(), "Eroor loading movies", Toast.LENGTH_SHORT).show();

            }
        });


    }
}
