package org.antem.movieapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    @GET("3/movie/top_rated")
    Call<Root> getTopRated(
            @Query("api_key") String key,
            @Query("language") String language,
            @Query("page") String page);


    @GET("3/movie/popular")
    Call<Root> getPopularMovies(
            @Query("api_key") String key,
            @Query("language") String language,
            @Query("page") String page);
}
