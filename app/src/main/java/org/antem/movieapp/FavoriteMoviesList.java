package org.antem.movieapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.antem.movieapp.MainActivity.FAVORITES;

public class FavoriteMoviesList {
    private SharedPreferences mSharedPreferences;
    private Context mContext;

    public FavoriteMoviesList(SharedPreferences sharedPreferences, Context context) {
        this.mSharedPreferences = sharedPreferences;
        this.mContext = context;
    }

    public void addOrDeleteMovie(Movie movie) {
        List<Movie> currentList;
        currentList = loadFavorites();

        List<Movie>newList=new ArrayList<>();

        boolean exist = false;
        if (currentList != null) {

            for (int i = 0; i < currentList.size(); i++) {
                if (currentList.get(i).getTitle().equals(movie.title)) {
                    exist = true;
                }else {
                    newList.add(currentList.get(i));
                }

            }
            if (!exist) {
                newList.add(movie);
                Toast.makeText(mContext, "Movie added to favorites", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mContext, "Movie removed from favorites", Toast.LENGTH_SHORT).show();
            }
        } else {

            newList.add(movie);
            Toast.makeText(mContext, "Movie added to favorites", Toast.LENGTH_SHORT).show();
        }
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String moviesJson = gson.toJson(newList);
        editor.putString(FAVORITES, moviesJson).apply();
    }

    public List<Movie> loadFavorites() {

        String favoritesJson = mSharedPreferences.getString(FAVORITES, "");
        Gson gson = new Gson();

        ArrayList<Movie> favMovieList;

        Type type = new TypeToken<ArrayList<Movie>>() {
        }.getType();
        favMovieList = gson.fromJson(favoritesJson, type);
        return favMovieList;

    }
}
