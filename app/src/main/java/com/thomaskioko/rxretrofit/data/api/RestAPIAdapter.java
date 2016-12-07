package com.thomaskioko.rxretrofit.data.api;


import com.thomaskioko.rxretrofit.util.ApplicationConstants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * API Client helper class used to configure Retrofit object.
 *
 * @author kioko
 */
public class RestAPIAdapter {

    private static Retrofit mRetrofit;
    private static boolean mIsDebug;
    private static HttpLoggingInterceptor mHttpLoggingInterceptor = new HttpLoggingInterceptor();


    /**
     * Configure OkHttpClient. This helps us override some of the default configuration. Like the
     * connection timeout.
     *
     * @return OkHttpClient
     */
    private static OkHttpClient okHttpClient() {

        return new OkHttpClient.Builder()
                .addInterceptor(new AuthInterceptor())
                .connectTimeout(ApplicationConstants.CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(ApplicationConstants.WRITE_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(ApplicationConstants.READ_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(mHttpLoggingInterceptor)
                .build();
    }

    /**
     * Create a new {@link Retrofit.Builder}. Override this to e.g. set your own client or executor.
     *
     * @return A {@link Retrofit.Builder} with no modifications.
     */
    private static Retrofit.Builder newRestAdapterBuilder() {
        return new Retrofit.Builder();
    }

    /**
     * Return the current {@link Retrofit} instance. If none exists (first call, API key changed),
     * builds a new one.
     * <p/>
     * When building, sets the endpoint and a {@link HttpLoggingInterceptor} which adds the API key as query param.
     */
    private static Retrofit getRestAdapter() {

        //Enable debugging based on the build type.
        RestAPIAdapter.setIsDebug(ApplicationConstants.DEBUG);

        Retrofit.Builder builder = null;
        if (mRetrofit == null) {

            //Create a new instance of the Rest Adapter class
            builder = newRestAdapterBuilder();

            builder.baseUrl(ApplicationConstants.END_POINT); //Set the endpoint.
            builder.client(okHttpClient());
            builder.addConverterFactory(GsonConverterFactory.create());
            builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        }

        if (mIsDebug) {
            if (builder != null) {
                mHttpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            }
        }

        if (builder != null) {
            mRetrofit = builder.build();
        }
        return mRetrofit;
    }

    /**
     * Creates a mRetrofit service from an arbitrary class.
     *
     * @param _class Java interface of the mRetrofit service
     * @return mRetrofit service with defined endpoint
     */
    public static <T> T createRetrofitService(final Class<T> _class) {

        return getRestAdapter().create(_class);
    }

    /**
     * Set the {@link Retrofit} log level.
     *
     * @param isDebug If true, the log level is set to
     *                {@link HttpLoggingInterceptor.Level#BODY}. Otherwise
     *                {@link HttpLoggingInterceptor.Level#NONE}.
     */
    private static void setIsDebug(boolean isDebug) {
        RestAPIAdapter.mIsDebug = isDebug;
        if (mRetrofit != null) {
            mHttpLoggingInterceptor.
                    setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        }
    }
}
