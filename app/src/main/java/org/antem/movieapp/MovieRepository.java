package org.antem.movieapp;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.ContentValues.TAG;

public class MovieRepository {
    private static final String MOVIE_DB_URL = "https://api.themoviedb.org/";
    public static final String API_KEY = "2c46288716a18fb7aadcc2a801f3fc6b";

    private MutableLiveData<Root> moviesLiveData;
    private MovieService movieService;

    public MovieRepository() {
        moviesLiveData = new MutableLiveData<>();


    }

    public void getTopRated() {
        movieService = new Retrofit.Builder()

                .baseUrl(MOVIE_DB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);
        movieService.getTopRated(API_KEY, "language=en-US", "1").enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                if (response.body() != null) {
                    moviesLiveData.setValue(response.body());
                }
                for (int i = 0; i < response.body().getResults().size(); i++) {
                    Log.i(TAG, "onResponse: "+response.body().getResults().get(i).getTitle());
                }
            }


            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());

            }
        });

    }

    public void getPopularMovies() {
        movieService = new Retrofit.Builder()

                .baseUrl(MOVIE_DB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieService.class);
        movieService.getPopularMovies(API_KEY, "language=en-US", "1").enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.body() != null) {
                    moviesLiveData.setValue(response.body());
                }

            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.i(TAG, "onFailure: " + t.toString());

            }
        });

    }

    public MutableLiveData<Root> getMoviesLiveData() {
        return moviesLiveData;
    }
}
