package com.thomaskioko.rxretrofit.data.services;


import com.thomaskioko.rxretrofit.model.MovieResult;

import retrofit2.http.GET;
import rx.Observable;


/**
 * This class contains API functions
 *
 * @author Kioko
 * @version Version
 */


public interface MovieServices {

    /**
     * Get top rated movies
     *
     * @return JSON Result
     */
    @GET("top_rated?")
    Observable<MovieResult> getTopRatedMovies();

    /**
     * Get popular movies.
     *
     * @return JSON Result
     */
    @GET("popular?")
    Observable<MovieResult> getPopularMovies();

}

