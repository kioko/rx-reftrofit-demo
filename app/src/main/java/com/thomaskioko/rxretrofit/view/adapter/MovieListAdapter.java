package com.thomaskioko.rxretrofit.view.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thomaskioko.rxretrofit.R;
import com.thomaskioko.rxretrofit.databinding.ListMovieItemBinding;
import com.thomaskioko.rxretrofit.model.Movie;
import com.thomaskioko.rxretrofit.util.DisplayUtils;

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

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = mMovieList.get(position);
        ListMovieItemBinding binding = DataBindingUtil.getBinding(holder.itemView);
        holder.bindTo(binding, movie);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    /**
     * Class that helps us implement {@link android.support.v7.widget.RecyclerView.ViewHolder} pattern
     * in the adapter.
     */
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Movie movie;
        private ViewDataBinding mViewDataBinding;

        ViewHolder(View itemView) {
            super(itemView);
            mViewDataBinding = DataBindingUtil.bind(itemView);
            itemView.setTag(mViewDataBinding);
        }

        /**
         * Helper method used to bind objects to the view.
         *
         * @param binding {@link ListMovieItemBinding}
         * @param movie   {@link Movie}
         */
        void bindTo(ListMovieItemBinding binding, Movie movie) {

            this.movie = movie;
            binding.setMovie(movie);
            binding.movieImage.setOnClickListener(this);
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.movie_image:
                    DisplayUtils.showToastMessage(view.getContext(), movie.getTitle(), true);
                    break;
            }
        }
    }

}

