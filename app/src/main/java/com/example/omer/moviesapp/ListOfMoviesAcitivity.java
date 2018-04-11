package com.example.omer.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Omer on 10/04/2018.
 */

public class ListOfMoviesAcitivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_movies_activity_layout);
        Intent intent = getIntent();

        mRecyclerView = (RecyclerView) findViewById(R.id.list_of_movies_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MoviesAdapter(ListOfMoviesAcitivity.this, (List<Movie>) intent.getSerializableExtra("list"), mRecyclerView);
        mRecyclerView.setAdapter(mAdapter);

    }
}
