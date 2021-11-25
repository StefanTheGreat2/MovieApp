package org.antem.movieapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    public static final String MOVIE_IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    private Context mContext;
    private List<Movie> movieList;

    public MoviesAdapter(Context context) {
        this.mContext = context;

    }


    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.single_movie_item, parent, false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesAdapter.MovieViewHolder holder, int position) {
        Movie movie = movieList.get(position);

        String currentImageURL=MOVIE_IMAGE_BASE_URL + movie.getPoster_path();

        Glide.with(holder.itemView)
                .load(currentImageURL)
                .into(holder.movieImage);

        holder.movieImage.setOnClickListener(click->{
            Intent intent=new Intent(mContext,MovieDetailsActivity.class);
            intent.putExtra("movie_title", movie.getTitle());
            intent.putExtra("description", movie.getOverview());
            intent.putExtra("release_date", movie.getRelease_date());
            intent.putExtra("image_url",currentImageURL);

            mContext.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        if (movieList != null) {

            return movieList.size();
        }
        return 0;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
        notifyDataSetChanged();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movieImage;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);

            movieImage = itemView.findViewById(R.id.movieImage);

        }
    }
}
