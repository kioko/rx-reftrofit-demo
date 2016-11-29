package com.thomaskioko.rxretrofit.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.thomaskioko.rxretrofit.R;
import com.thomaskioko.rxretrofit.data.api.ServiceFactory;
import com.thomaskioko.rxretrofit.data.services.MovieServices;
import com.thomaskioko.rxretrofit.databinding.ActivityMainBinding;
import com.thomaskioko.rxretrofit.model.MovieResult;
import com.thomaskioko.rxretrofit.util.DeviceUtils;
import com.thomaskioko.rxretrofit.view.adapter.MovieListAdapter;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding mActivityMainBinding;

    private MovieServices mMovieServices;
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(this, 3);

        mActivityMainBinding.recyclerViewList.setLayoutManager(recyclerViewLayoutManager);


        mMovieServices = ServiceFactory.createRetrofitService(MovieServices.class);

        if (DeviceUtils.isNetworkConnected(this)) {
            getPopularMovies();
        } else {
            mActivityMainBinding.textViewMessage.setVisibility(View.VISIBLE);
            mActivityMainBinding.textViewMessage.setText(getString(R.string.error_no_internet_connection));
        }
    }

    /**
     * Helper method that fetches popular movies.
     */
    private void getPopularMovies() {

        mMovieServices.getPopularMovies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieResult>() {
                    @Override
                    public void onCompleted() {
                        mActivityMainBinding.textViewMessage.setVisibility(View.GONE);
                        mActivityMainBinding.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mActivityMainBinding.progressBar.setVisibility(View.GONE);
                        mActivityMainBinding.textViewMessage.setVisibility(View.VISIBLE);
                        mActivityMainBinding.textViewMessage.setText(e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(MovieResult movieResult) {

                        mActivityMainBinding.recyclerViewList.setAdapter(new MovieListAdapter(movieResult.getResults()));
                    }
                });
    }
}
