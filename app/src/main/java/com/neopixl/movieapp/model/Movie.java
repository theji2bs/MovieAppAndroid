package com.neopixl.movieapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Yvan Moté on 12/05/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie implements Parcelable {
    private String title;

    @JsonProperty(value = "poster_path")
    private String posterPath; //poster_path

    /*
    public Movie(String title) {
        this.title = title;
    }
    */
    // La lecture (read XXXX) doit se faire dans le meme ordre que l'ecriture (write)
    protected Movie(Parcel in) {
        title = in.readString();
        posterPath = in.readString();
    }


        public Movie(){

        }
    // L'écriture (writeXXXX doit se faire
    //dans le meme ordre que la lecture
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(posterPath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
