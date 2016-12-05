package com.thomaskioko.rxretrofit;

import android.app.Application;

import timber.log.Timber;

/**
 * @author Thomas Kioko
 */

public class RxDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
