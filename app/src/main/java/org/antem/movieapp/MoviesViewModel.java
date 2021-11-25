package org.antem.movieapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MoviesViewModel extends AndroidViewModel {
    private MovieRepository mMovieRepository;
    private LiveData<Root> moviesLiveData;


    public MoviesViewModel(@NonNull Application application) {
        super(application);
        mMovieRepository = new MovieRepository();
        getPopularMovies();
        moviesLiveData = mMovieRepository.getMoviesLiveData();
    }

    public LiveData<Root> getMoviesLiveData() {
        return moviesLiveData;
    }

    public void getTopRatedMovies() {
        mMovieRepository.getTopRated();
    }

    public void getPopularMovies() {
        mMovieRepository.getPopularMovies();
    }
}
