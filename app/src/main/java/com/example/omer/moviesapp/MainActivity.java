package com.example.omer.moviesapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.omer.moviesapp.search.SearchFragment;
import com.example.omer.moviesapp.top_rated_movie.TopRatedFragment;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.navigation_menu);
        bottomNavigationView.setSelectedItemId(R.id.menu_top_rated);

        Fragment topRatedFragment = new TopRatedFragment();
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, topRatedFragment);
        ft.commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_search:
                        Fragment searchFragment = new SearchFragment();
                        FragmentManager fm = getSupportFragmentManager();

                        FragmentTransaction ft = fm.beginTransaction();
                        ft.replace(R.id.fragment_container, searchFragment);
                        ft.commit();
                        break;
                    case R.id.menu_fav:


                    case R.id.menu_top_rated:

                        Fragment topRatedFragment = new TopRatedFragment();
                        FragmentManager fm2 = getSupportFragmentManager();

                        FragmentTransaction ft2 = fm2.beginTransaction();
                        ft2.replace(R.id.fragment_container, topRatedFragment);
                        ft2.commit();
                        break;
                }

                return true;
            }
        });


    }
}
