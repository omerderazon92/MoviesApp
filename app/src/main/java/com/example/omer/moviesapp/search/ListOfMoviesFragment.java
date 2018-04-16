package com.example.omer.moviesapp.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.omer.moviesapp.Movie;
import com.example.omer.moviesapp.R;
import com.example.omer.moviesapp.top_rated_movie.ItemOffsetDecoration;

import java.util.List;

public class ListOfMoviesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.list_of_movies_activity_layout, container, false);
        mRecyclerView = view.findViewById(R.id.list_of_movies_recycler_view);
        mLayoutManager = new LinearLayoutManager(ListOfMoviesFragment.this.getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(ListOfMoviesFragment.this.getContext(), R.dimen.cardview_default_radius);
        mRecyclerView.addItemDecoration(itemDecoration);
        mRecyclerView.setHasFixedSize(true);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final List<Movie> list = (List<Movie>) getArguments().getSerializable("list");
        mAdapter = new SearchedMoviesAdapter(ListOfMoviesFragment.this.getContext(), list, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);
    }
}
