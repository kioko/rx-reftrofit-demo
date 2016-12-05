package com.thomaskioko.rxretrofit.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;
import android.widget.Toast;

import com.thomaskioko.rxretrofit.model.Movie;

/**
 * This class extends {@link BaseObservable} allowing the binding to attach a listener to an object.
 *
 * @author Thomas Kioko
 */

public class MovieViewModel extends BaseObservable {

    private Context mContext;
    private Movie mMovie;
    private static final String LOG_TAG = MovieViewModel.class.getSimpleName();

    /**
     * Constructor.
     *
     * @param context {@link Context} Context in which the class is called.
     * @param movie   {@link Movie} Movie Object
     */
    public MovieViewModel(Context context, Movie movie) {
        mContext = context;
        mMovie = movie;
    }

    /**
     * Helper class to handle oClick events on a movie item.
     *
     * @return {@link android.view.View.OnClickListener}
     */
    public View.OnClickListener onClickImage() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Do Something
                Toast.makeText(mContext, mMovie.getTitle(), Toast.LENGTH_SHORT).show();
            }
        };
    }
}
