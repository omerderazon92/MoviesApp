package com.example.omer.moviesapp.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.omer.moviesapp.Movie;
import com.example.omer.moviesapp.R;

import java.util.List;

public class ListOfMoviesAcitivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_movies_activity_layout);
        Intent intent = getIntent();

        mRecyclerView = findViewById(R.id.list_of_movies_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        final List<Movie> list = (List<Movie>) intent.getSerializableExtra("list");
        mAdapter = new MoviesAdapter(ListOfMoviesAcitivity.this,  list, mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

    }
}
