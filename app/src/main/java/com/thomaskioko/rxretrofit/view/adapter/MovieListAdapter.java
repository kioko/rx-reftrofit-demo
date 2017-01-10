package com.thomaskioko.rxretrofit.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomaskioko.rxretrofit.BR;
import com.thomaskioko.rxretrofit.R;
import com.thomaskioko.rxretrofit.databinding.ListMovieItemBinding;
import com.thomaskioko.rxretrofit.model.Movie;
import com.thomaskioko.rxretrofit.view.util.ListenerBinding;

import java.util.List;

/**
 * @author Thomas Kioko
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Movie> mMovieList;

    /**
     * Constructor
     *
     * @param movieList List of movie objects.
     */
    public MovieListAdapter(List<Movie> movieList) {
        mMovieList = movieList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_movie_item, parent, false);
        ListMovieItemBinding binding = ListMovieItemBinding.bind(view);

        //Set listener
        binding.setListeners(new ListenerBinding(binding));

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        ViewDataBinding binding = holder.getBinding();
        binding.setVariable(BR.movie, movie); //Bind the variable
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    /**
     * Class that helps us implement {@link android.support.v7.widget.RecyclerView.ViewHolder} pattern
     * in the adapter.
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        ViewHolder(View itemView) {
            super(itemView);
        }

        /**
         * Method that returns Binding instance.
         *
         * @return {@link ListMovieItemBinding}
         */
        ViewDataBinding getBinding() {
            return DataBindingUtil.getBinding(itemView);
        }
    }

}

