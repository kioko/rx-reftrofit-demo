package com.thomaskioko.rxretrofit.view.adapter;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.thomaskioko.rxretrofit.BR;
import com.thomaskioko.rxretrofit.R;
import com.thomaskioko.rxretrofit.databinding.ListMovieItemBinding;
import com.thomaskioko.rxretrofit.model.Movie;
import com.thomaskioko.rxretrofit.util.ApplicationConstants;

import java.util.List;

/**
 * @author Thomas Kioko
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Movie> mMovieList;
    private static final String LOG_TAG = MovieListAdapter.class.getSimpleName();

    /**
     * Constructor
     *
     * @param movieList
     */
    public MovieListAdapter(List<Movie> movieList) {
        mMovieList = movieList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ListMovieItemBinding binding = ListMovieItemBinding.inflate(inflater, parent, false);

        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movie movie = mMovieList.get(position);
        holder.getViewDataBinding().setVariable(BR.movie, movie);
        holder.getViewDataBinding().executePendingBindings();

    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    /**
     *
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mViewDataBinding;

        ViewHolder(View itemView) {
            super(itemView);
            mViewDataBinding = DataBindingUtil.bind(itemView);
            itemView.setTag(mViewDataBinding);
        }

        public ViewDataBinding getViewDataBinding(){
            return mViewDataBinding;
        }
    }

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {

        Glide.with(imageView.getContext())
                .load(ApplicationConstants.TMDB_IMAGE_URL + ApplicationConstants.IMAGE_SIZE_780 + url)
                .centerCrop()
                .placeholder(R.color.placeholder)
                .into(imageView);
    }
}

