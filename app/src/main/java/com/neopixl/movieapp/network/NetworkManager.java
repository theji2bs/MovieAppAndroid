package com.neopixl.movieapp.network;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.SimpleType;
import com.neopixl.movieapp.MovieApplication;
import com.neopixl.movieapp.model.Movie;
import com.neopixl.movieapp.model.MovieResult;
import com.spothero.volley.JacksonRequest;
import com.spothero.volley.JacksonRequestListener;

/**
 * Created by Yvan Moté on 12/05/2016.
 */
public class NetworkManager {

    public interface MovieResultListener {
        void onFindMovies(Movie[] movies);
        void onFail();
    }

    public static void findMovies(String title,
                           final MovieResultListener listener) {

        String url = "http://api.themoviedb.org/3/search/movie?api_key=c1ac741d5dd740f9861e794c5363b0c2&query=alien";

        JacksonRequest<MovieResult> request =
                new JacksonRequest<MovieResult>(Request.Method.GET, url,
                        new JacksonRequestListener<MovieResult>() {
                    @Override
                    public void onResponse(MovieResult response, int statusCode, VolleyError error) {

                        if(error!=null) {
                            if(listener!=null) {
                                listener.onFail();
                            }
                        } else {
                            if(response!=null) {
                                if(listener!=null) {
                                    listener.
                                            onFindMovies(response.getResults());
                                }
                            }
                        }
                    }

                    @Override
                    public JavaType getReturnType() {
                        /*
                        return ArrayType.construct(SimpleType.construct(Movie.class),
                                null, null);
                         */
                        return SimpleType.construct(MovieResult.class);
                    }
                });

        //Exécution de la requête
        MovieApplication.getSingleInstance()
                .getRequestQueue().add(request);

    }

}
