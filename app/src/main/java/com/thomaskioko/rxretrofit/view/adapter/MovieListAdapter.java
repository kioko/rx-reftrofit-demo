package com.thomaskioko.rxretrofit.view.adapter;

import android.content.Context;
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
import com.thomaskioko.rxretrofit.viewmodel.MovieViewModel;

import java.util.List;

/**
 * @author Thomas Kioko
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {

    private List<Movie> mMovieList;
    private Context mContext;

    /**
     * Constructor
     *
     * @param context   {@link Context }Context in which the class is callled
     * @param movieList List of movie objects.
     */
    public MovieListAdapter(Context context, List<Movie> movieList) {
        mMovieList = movieList;
        mContext = context;

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
        //set values to the Binding class.
        holder.getViewDataBinding().setVariable(BR.movie, movie);
        holder.getViewDataBinding().setVariable(BR.viewModel, new MovieViewModel(mContext, movie));
        
        //Evaluates the pending bindings, updating any Views that have expressions bound to modified variables
        holder.getViewDataBinding().executePendingBindings();

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

        private ViewDataBinding mViewDataBinding;

        ViewHolder(View itemView) {
            super(itemView);
            mViewDataBinding = DataBindingUtil.bind(itemView);
            itemView.setTag(mViewDataBinding);
        }

        ViewDataBinding getViewDataBinding() {
            return mViewDataBinding;
        }
    }

    /**
     * Method that loads an imageView using {@link Glide}
     *
     * @param imageView {@link ImageView} Item ImageView
     * @param url       {@link Movie#getPosterUrl()} Poster Url from the Movie Object.
     */
    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView imageView, String url) {

        Glide.with(imageView.getContext())
                .load(ApplicationConstants.TMDB_IMAGE_URL + ApplicationConstants.IMAGE_SIZE_780 + url)
                .centerCrop()
                .placeholder(R.color.placeholder)
                .into(imageView);
    }
}

