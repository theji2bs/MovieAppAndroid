package com.neopixl.movieapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by Yvan Moté on 12/05/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResult {
    private int page;
    private Movie[] results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public Movie[] getResults() {
        return results;
    }

    public void setResults(Movie[] results) {
        this.results = results;
    }
}
