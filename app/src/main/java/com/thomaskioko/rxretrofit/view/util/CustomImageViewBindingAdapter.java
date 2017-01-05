package com.thomaskioko.rxretrofit.view.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;
import com.thomaskioko.rxretrofit.R;
import com.thomaskioko.rxretrofit.model.Movie;
import com.thomaskioko.rxretrofit.util.ApplicationConstants;

/**
 * This class uses {@link com.bumptech.glide.Glide} to load images
 *
 * @author Thomas Kioko
 */

public class CustomImageViewBindingAdapter {

    private static Picasso picassoInstance;

    /**
     * Method that returns an instance of {@link Picasso} object
     *
     * @param context {@link Context}
     * @return the singleton
     */
    private static Picasso getGlideInstance(Context context) {

        if (picassoInstance == null) {
            picassoInstance = Picasso.with(context);
        }

        return picassoInstance;
    }


    /**
     * Method that loads an imageView using {@link Glide}
     *
     * @param imageView {@link ImageView} Item ImageView
     * @param url       {@link Movie#getPosterUrl()} Poster Url from the Movie Object.
     */
    @BindingAdapter("bind:imageUrl")
    public static void setImage(ImageView imageView, String url) {

        picassoInstance = getGlideInstance(imageView.getContext());
        picassoInstance.load(ApplicationConstants.TMDB_IMAGE_URL + ApplicationConstants.IMAGE_SIZE_780 + url)
                .placeholder(R.color.placeholder)
                .into(imageView);
    }


    /**
     * Method that loads an imageView using {@link Glide}
     *
     * @param imageView        {@link ImageView} Item ImageView
     * @param url              {@link Movie#getPosterUrl()} Poster Url from the Movie Object.
     * @param imagePlaceHolder
     * @param imageError
     */
    @BindingAdapter({"bind:imageUrl", "bind:imagePlaceHolder", "bind:imageError"})
    public static void setImage(ImageView imageView, String url, Drawable imagePlaceHolder,
                                Drawable imageError) {

        picassoInstance = getGlideInstance(imageView.getContext());
        picassoInstance.load(ApplicationConstants.TMDB_IMAGE_URL + ApplicationConstants.IMAGE_SIZE_780 + url)
                .placeholder(imagePlaceHolder)
                .error(imageError)
//                .placeholder(R.color.placeholder)
                .into(imageView);
    }
}
