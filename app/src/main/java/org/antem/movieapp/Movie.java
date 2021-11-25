package org.antem.movieapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Movie {
    public Movie(String poster_path, String title, String overview, String release_date) {
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.title = title;
    }

    @SerializedName("overview")
    @Expose
    public String overview;
    @SerializedName("poster_path")
    @Expose
    public String poster_path;
    @SerializedName("release_date")
    @Expose
    public String release_date;
    @SerializedName("title")
    @Expose
    public String title;





    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public String getTitle() {
        return title;
    }


}

