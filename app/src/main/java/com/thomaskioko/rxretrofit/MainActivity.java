package com.thomaskioko.rxretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.thomaskioko.rxretrofit.api.ServiceFactory;
import com.thomaskioko.rxretrofit.model.MovieResult;
import com.thomaskioko.rxretrofit.service.MovieServices;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private  MovieServices mMovieServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieServices = ServiceFactory.createRetrofitService(MovieServices.class);

        getPopularMovies();

    }

    /**
     * Helper method that fetches popular movies.
     */
    private void getPopularMovies(){

        mMovieServices.getPopularMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieResult movieResult) {

                    }
                });
    }
}
