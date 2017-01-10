package com.thomaskioko.rxretrofit.viewmodel;

import android.databinding.BindingAdapter;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.thomaskioko.rxretrofit.model.Movie;
import com.thomaskioko.rxretrofit.view.adapter.MovieListAdapter;

import java.util.List;

/**
 * @author Thomas Kioko
 */

public class MainActivityBinding {

    /**
     * Methods that sets the layout of the RecyclerView and pass list of movies to the adapter class.
     *
     * @param recyclerView {@link RecyclerView}
     * @param movieList    {@link Movie} List of objects.
     */
    @BindingAdapter("bind:movieList")
    public static void setMovieList(RecyclerView recyclerView, List<Movie> movieList) {

        //Set the layout of the recyclerview.
        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        MovieListAdapter movieListAdapter = (MovieListAdapter) recyclerView.getAdapter();

        if (movieListAdapter == null) {

            if (movieList != null) {
                movieListAdapter = new MovieListAdapter(movieList);
                recyclerView.setAdapter(movieListAdapter);
            }
        }
    }
}
