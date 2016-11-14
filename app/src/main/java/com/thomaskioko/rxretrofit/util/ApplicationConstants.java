package com.thomaskioko.rxretrofit.util;

import com.thomaskioko.rxretrofit.BuildConfig;

/**
 * @author Thomas Kioko
 */


public class ApplicationConstants {

    /**
     * Check if Build type is debug
     */
    public static final boolean DEBUG = BuildConfig.DEBUG;
    /**
     * API Endpoint
     */
    public static final String END_POINT = "http://api.themoviedb.org/3/movie/";
    /**
     * Connection timeout duration
     */
    public static final int CONNECT_TIMEOUT = 60 * 1000;
    /**
     * Connection Read timeout duration
     */
    public static final int READ_TIMEOUT = 60 * 1000;
    /**
     * Connection write timeout duration
     */
    public static final int WRITE_TIMEOUT = 60 * 1000;

}
