package com.example.omer.moviesapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText key_words_to_search;
    Button button;
    public static final String MOVIE_DATABASE_BASE_URL = "https://api.themoviedb.org/3/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        key_words_to_search = (EditText) findViewById(R.id.place_for_search);
        button = (Button) findViewById(R.id.ssNq);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MOVIE_DATABASE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ServerCall serverCall = retrofit.create(ServerCall.class);

        final MoviesRepository moviesRepository = new MoviesRepository(serverCall);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWordsToSearch = String.valueOf(key_words_to_search.getText());
                moviesRepository.getListOfMovies(keyWordsToSearch, new GetMoviesCallback() {
                    @Override
                    public void onMoviesReceived(List<Movie> movies) {
                        Intent intent = new Intent(MainActivity.this, ListOfMoviesAcitivity.class);
                        intent.putExtra("list", (Serializable) movies);
                        startActivity(intent);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
            }
        });


    }
}
