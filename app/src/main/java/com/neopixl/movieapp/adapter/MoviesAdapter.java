package com.neopixl.movieapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.neopixl.movieapp.R;
import com.neopixl.movieapp.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yvan Moté on 12/05/2016.
 */
public class MoviesAdapter extends BaseAdapter {

    private List<Movie> movies;
    // Parser + instanciateur de fichiers layout XML
    private LayoutInflater layoutInflater;

    public MoviesAdapter(Context context) {
        movies = new ArrayList<>();
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View viewFromCache, ViewGroup viewGroup) {


        //pas de vue en cache
        if(viewFromCache==null) {
            boolean attachToParent = false;

            //Création de cellule (coûteux)
            viewFromCache = layoutInflater.inflate(R.layout.movie_row, viewGroup, attachToParent);
            //findViewById fait uniquement dans le constructeur RowMovieHolder
            viewFromCache.setTag(new RowMovieHolder(viewFromCache));
        }

        Movie movie = movies.get(i);

        //RowMovieHolder = mise en cache des findViewById sur viewFromCache
        //Top performance w00t \o/!!!
        RowMovieHolder rowMovieHolder = (RowMovieHolder) viewFromCache.getTag();
        rowMovieHolder.refreshWithMovie(movie);

        return viewFromCache;
    }

    public void refreshWithMovies(List<Movie> movies) {
        this.movies.clear();            //on supprime le cache
        this.movies.addAll(movies);

        //Refresh UI: rechargement des cellules
        notifyDataSetChanged();
    }


}
