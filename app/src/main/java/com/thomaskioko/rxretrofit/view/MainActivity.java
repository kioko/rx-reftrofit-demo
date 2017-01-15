package com.thomaskioko.rxretrofit.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.thomaskioko.rxretrofit.R;
import com.thomaskioko.rxretrofit.data.api.IMovieAPI;
import com.thomaskioko.rxretrofit.data.api.RestAPIAdapter;
import com.thomaskioko.rxretrofit.databinding.ActivityMainBinding;
import com.thomaskioko.rxretrofit.model.MovieResult;
import com.thomaskioko.rxretrofit.util.DeviceUtils;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    ActivityMainBinding mActivityMainBinding;
    private IMovieAPI mIMovieAPI;
    private Subscription mSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (DeviceUtils.isNetworkConnected(this)) {
            mIMovieAPI = RestAPIAdapter.createRetrofitService(IMovieAPI.class);
            loadPopularMovies();
        } else {
            mActivityMainBinding.textViewMessage.setVisibility(View.VISIBLE);
            mActivityMainBinding.textViewMessage.setText(getString(R.string.error_no_internet_connection));
        }
    }

    /**
     * Helper method that fetches popular movies.
     */
    private void loadPopularMovies() {

        mSubscription = mIMovieAPI.getPopularMovies()
                .subscribeOn(Schedulers.io()) //Run this task in the io thread
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
                        mActivityMainBinding.setMovieList(movieResult.getResults());
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }
}
