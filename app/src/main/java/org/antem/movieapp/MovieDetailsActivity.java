package org.antem.movieapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import static org.antem.movieapp.MainActivity.FAVORITES;


public class MovieDetailsActivity extends AppCompatActivity {

    ImageView movieImage;
    TextView movieTitle;
    TextView description;
    TextView releaseDate;
    Button addToFavorites;
    FavoriteMoviesList mFavoriteMoviesList;
    Movie movie;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_details_activity);
        movieImage = findViewById(R.id.detailesMovieImage);
        movieTitle = findViewById(R.id.movieTitle);
        description = findViewById(R.id.movieDescription);
        releaseDate = findViewById(R.id.movieReleaseDate);
        addToFavorites = findViewById(R.id.addToFavorites);

        mFavoriteMoviesList = new FavoriteMoviesList(getSharedPreferences(FAVORITES, MODE_PRIVATE), this);
        movie = new Movie(
                getIntent().getStringExtra("image_url"),
                getIntent().getStringExtra("movie_title"),
                getIntent().getStringExtra("description"),
                getIntent().getStringExtra("release_date"));

        Glide.with(movieImage).load(movie.getPoster_path()).into(movieImage);
        movieTitle.setText(movie.getTitle());
        description.setText("Description: " + movie.getOverview());
        releaseDate.setText("Release Date :" + movie.getRelease_date());
        Log.i("TAG", "onCreate: " + getIntent().getParcelableExtra("detail"));

        addToFavorites.setOnClickListener(click -> {
            addOrRemove();
        });

    }

    private void addOrRemove() {
        mFavoriteMoviesList.addOrDeleteMovie(movie);
    }
}
