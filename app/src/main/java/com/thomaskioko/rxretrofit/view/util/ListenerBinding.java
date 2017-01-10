package com.thomaskioko.rxretrofit.view.util;

import android.view.View;

import com.thomaskioko.rxretrofit.R;
import com.thomaskioko.rxretrofit.databinding.ListMovieItemBinding;
import com.thomaskioko.rxretrofit.util.DisplayUtils;

/**
 * Binding class to handle on click events.
 *
 * @author Thomas Kioko
 */

public class ListenerBinding implements View.OnClickListener {

    private ListMovieItemBinding mListMovieItemBinding;

    /**
     * Constructor
     *
     * @param listMovieItemBinding Binding item
     */
    public ListenerBinding(ListMovieItemBinding listMovieItemBinding) {
        mListMovieItemBinding = listMovieItemBinding;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.movie_image:
                DisplayUtils.showToastMessage(view.getContext(), mListMovieItemBinding.getMovie().getTitle(), true);
                break;
        }
    }
}
