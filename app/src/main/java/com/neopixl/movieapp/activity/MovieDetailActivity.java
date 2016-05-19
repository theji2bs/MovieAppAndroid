package com.neopixl.movieapp.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.neopixl.movieapp.MovieApplication;
import com.neopixl.movieapp.R;
import com.neopixl.movieapp.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "movie_extra" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // recuperation de l'objet movie


        NetworkImageView networkImageView = (NetworkImageView)findViewById(R.id.movie_detail_imageview);

        TextView textView = (TextView)findViewById(R.id.movie_detail_textview);

        // on recupere l'intent

        Intent intent = getIntent();
        if (intent!=null) {
            Movie movie = intent.getParcelableExtra(EXTRA_MOVIE);

            textView.setText(movie.getTitle());
            ImageLoader imageLoader = ((MovieApplication)getApplication()).getImageLoader();

            String url = "https://image.tmdb.org/t/p/w130";
            String posterUrl = url+movie.getPosterPath();
            networkImageView.setImageUrl(posterUrl, imageLoader);
        }
    }
}
