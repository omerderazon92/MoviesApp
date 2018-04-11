package com.example.omer.moviesapp.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.omer.moviesapp.GetMoviesCallback;
import com.example.omer.moviesapp.Movie;
import com.example.omer.moviesapp.MoviesRepository;
import com.example.omer.moviesapp.R;
import com.example.omer.moviesapp.ServerCall;

import java.io.Serializable;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SearchFragment extends Fragment {

    EditText key_words_to_search;
    Button button;
    public static final String MOVIE_DATABASE_BASE_URL = "https://api.themoviedb.org/3/";


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        key_words_to_search = view.findViewById(R.id.place_for_search);
        button = view.findViewById(R.id.ssNq);

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
                        Intent intent = new Intent(SearchFragment.this.getContext(), ListOfMoviesAcitivity.class);
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