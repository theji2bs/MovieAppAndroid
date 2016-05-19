package com.neopixl.movieapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.neopixl.movieapp.activity.MovieDetailActivity;
import com.neopixl.movieapp.adapter.MoviesAdapter;
import com.neopixl.movieapp.model.Movie;
import com.neopixl.movieapp.network.NetworkManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private MoviesAdapter moviesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Movie> movies = new ArrayList<>();
        /*
        movies.add(new Movie("Alien"));
        movies.add(new Movie("Hunger games"));
        movies.add(new Movie("Harry Potter"));
        movies.add(new Movie("Twilight"));
        movies.add(new Movie("American Pie"));
        movies.add(new Movie("Star wars"));
        movies.add(new Movie("Terminator"));
        movies.add(new Movie("Titanic"));
        movies.add(new Movie("Le journal de Bridget Jones"));
        movies.add(new Movie("Mad Max"));
        movies.add(new Movie("Wall-e"));
        movies.add(new Movie("La vie est un long fleuve tranquille"));
        movies.add(new Movie("Tonnerre sous les tropiques"));
        movies.add(new Movie("C'est arrivé près de chez vous"));
        movies.add(new Movie("Dikkenek"));
        movies.add(new Movie("Maman j'ai raté l'avion."));
        */


        // this = l'instance courante de MainActivity = sous-classe de Context
        moviesAdapter = new MoviesAdapter(this);
        moviesAdapter.refreshWithMovies(movies);

        ListView listView = (ListView) findViewById(R.id.movies_listview);
        listView.setOnItemClickListener(this);

        listView.setAdapter(moviesAdapter);

        NetworkManager.findMovies("Terminator", new NetworkManager.MovieResultListener() {
            @Override
            public void onFindMovies(Movie[] movies) {
                int numberOfMovies = movies.length;

                List<Movie> movieList = Arrays.asList(movies);

                moviesAdapter.refreshWithMovies(movieList);

            }

            @Override
            public void onFail() {
                int a = 10;
            }
        });



    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie movie = (Movie) moviesAdapter.getItem(position);
        // Transfer du movie dans une nouvelle vue

        Intent intent = new Intent(this,MovieDetailActivity.class);

        intent.putExtra(MovieDetailActivity.EXTRA_MOVIE,movie);

        // Lancer ou tenter l'écran

        startActivity(intent);



    }
}
