package com.thomaskioko.rxretrofit.data.api;


import com.thomaskioko.rxretrofit.model.MovieResult;

import retrofit2.http.GET;
import rx.Observable;


/**
 * This interface contains all API endpoints.
 *
 * @author Kioko
 */


public interface IMovieAPI {

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

