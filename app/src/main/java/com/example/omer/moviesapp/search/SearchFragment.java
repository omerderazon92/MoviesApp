package com.example.omer.moviesapp.search;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.omer.moviesapp.GetMoviesCallback;
import com.example.omer.moviesapp.MainActivity;
import com.example.omer.moviesapp.Movie;
import com.example.omer.moviesapp.MoviesRepository;
import com.example.omer.moviesapp.MoviesServiceProvider;
import com.example.omer.moviesapp.R;

import java.io.Serializable;
import java.util.List;


public class SearchFragment extends Fragment {

    EditText keyWordsToSearchEditTextView;
    Button button;
    MoviesRepository moviesRepository;


    public SearchFragment() {
        this.moviesRepository = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviesRepository = new MoviesRepository(MoviesServiceProvider.getMoviesService());


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        keyWordsToSearchEditTextView = view.findViewById(R.id.place_for_search);
        button = view.findViewById(R.id.ssNq);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWordsToSearch = String.valueOf(SearchFragment.this.keyWordsToSearchEditTextView.getText());
                moviesRepository.getListOfMovies(keyWordsToSearch, new GetMoviesCallback() {
                    @Override
                    public void onMoviesReceived(List<Movie> movies) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("list", (Serializable) movies);
                        Fragment listOfMoviesFragment = new ListOfMoviesFragment();
                        FragmentManager fm = ((MainActivity) getContext()).getSupportFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        listOfMoviesFragment.setArguments(bundle);
                        ft.replace(R.id.fragment_container, listOfMoviesFragment);
                        ft.addToBackStack(null);
                        ft.commit();
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
            }
        });

    }
}