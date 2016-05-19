package com.neopixl.movieapp;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.neopixl.movieapp.network.LruBitmapCache;

/**
 * Created by Yvan Moté on 12/05/2016.
 */
public class MovieApplication extends Application {

    private RequestQueue requestQueue;
    //singleton
    private static MovieApplication singleInstance;
    private ImageLoader imageLoader;

    @Override
    public void onCreate() {
        super.onCreate();

        //On garde une référence de l'instance courante
        //en tant que Singleton
        MovieApplication.singleInstance = this;

        requestQueue = Volley.newRequestQueue(this);

        LruBitmapCache lruBitmapCache = new LruBitmapCache(8 * 1024 * 1024); //8Mo

        imageLoader = new ImageLoader(requestQueue, lruBitmapCache);
    }

    public static MovieApplication getSingleInstance() {
        return singleInstance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}
