package com.example.omer.moviesapp.top_rated_movie;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.omer.moviesapp.GetPictureCallBack;
import com.example.omer.moviesapp.MainActivity;
import com.example.omer.moviesapp.Movie;
import com.example.omer.moviesapp.R;
import com.example.omer.moviesapp.search.PictureDownloadHandler;
import com.squareup.picasso.RequestCreator;

public class InfoFragment extends Fragment {

    ImageView moviePicture, imageOfBar;
    TextView information, title;

    public InfoFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        postponeEnterTransition();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
        }

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.info_fragment_layout, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        moviePicture = view.findViewById(R.id.image_of_info);
        information = view.findViewById(R.id.movie_information_fragment);
        imageOfBar = view.findViewById(R.id.image_of_bar);
        title = view.findViewById(R.id.title_movie_inormation);

        String transitionName = getArguments().getString("transitionName");
        final Movie movie = (Movie) getArguments().getSerializable("movie");


        PictureDownloadHandler pdh = new PictureDownloadHandler(getArguments().getString("posterPath"), InfoFragment.this.getContext());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            moviePicture.setTransitionName(transitionName);
        }
        pdh.GetImageFromURL(new GetPictureCallBack() {
            @Override
            public void onPictureLoaded(RequestCreator requestCreator) {
                requestCreator.into(moviePicture);
                requestCreator.into(imageOfBar);
                startPostponedEnterTransition();
                Animation fadeInAnimation = AnimationUtils.loadAnimation((MainActivity) getContext(), android.R.anim.fade_in);
                title.setText(movie.getTitle());
                fadeInAnimation.setStartOffset(1000);
                information.setText(movie.getOverview());
                information.startAnimation(fadeInAnimation);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }


}
