package org.antem.movieapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



import java.util.List;

public class Root {
    @SerializedName("page")
    @Expose
    public int page;
    @SerializedName("results")
    @Expose
    public List<Movie> mMovies;


    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return mMovies;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setResults(List<Movie> movies) {
        this.mMovies = movies;
    }
}
