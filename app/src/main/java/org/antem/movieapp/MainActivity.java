package org.antem.movieapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String FAVORITES = "favorites";

    TextView sortedBy;
    RecyclerView movieRecycler;
    MoviesAdapter mMoviesAdapter;
    MovieRepository mMovieRepository;
    MoviesViewModel mMoviesViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sortedBy = findViewById(R.id.sorted_by);
        movieRecycler = findViewById(R.id.movie_recycler);


        mMovieRepository = new MovieRepository();
        mMoviesAdapter = new MoviesAdapter(this);
        movieRecycler.setLayoutManager(new GridLayoutManager(this, 3));
        movieRecycler.setAdapter(mMoviesAdapter);

        mMoviesViewModel = new ViewModelProvider(this).get(MoviesViewModel.class);

        mMoviesViewModel.getMoviesLiveData().observe(this, list -> {

            mMoviesAdapter.setMovieList(list.getResults());

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.top_rated) {
            mMoviesViewModel.getTopRatedMovies();
            sortedBy.setText("Top Rated Movies");


        } else if (item.getItemId() == R.id.popular) {

            mMoviesViewModel.getPopularMovies();
            sortedBy.setText("Most Popular Movies");

        } else if (item.getItemId() == R.id.favorites) {
            loadFavorites();
            sortedBy.setText("Favorites");
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFavorites() {
      FavoriteMoviesList favMovieList=new FavoriteMoviesList( getSharedPreferences(FAVORITES, MODE_PRIVATE),this);

        if (favMovieList.loadFavorites() != null) {
            mMoviesAdapter.setMovieList(favMovieList.loadFavorites());
        } else {
            mMoviesAdapter.setMovieList(new ArrayList<>());
        }
    }
}