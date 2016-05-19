package com.neopixl.movieapp.adapter;

import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.neopixl.movieapp.MovieApplication;
import com.neopixl.movieapp.R;
import com.neopixl.movieapp.model.Movie;

public class RowMovieHolder {

    private final TextView textViewMovieTitle;
    private final NetworkImageView posterNetworkImageView;

    public RowMovieHolder(View view) {
        textViewMovieTitle = (TextView) view.
                findViewById(R.id.row_movie_title_textview);
        posterNetworkImageView = (NetworkImageView) view.
                findViewById(R.id.row_movie_poster_imageview);

    }

    public void refreshWithMovie(Movie movie) {
        textViewMovieTitle.setText(movie.getTitle());
        String url = "https://image.tmdb.org/t/p/w130";
        String posterUrl = url+movie.getPosterPath();

        ImageLoader imageLoader =
                MovieApplication.
                        getSingleInstance().
                        getImageLoader();

        //Chargement de l'image à partir de l'url ou du cache
        //w00t \o/ !!!
        posterNetworkImageView.setImageUrl(posterUrl, imageLoader);

    }


}
