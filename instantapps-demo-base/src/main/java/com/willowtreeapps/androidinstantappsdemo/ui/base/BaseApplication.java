package com.willowtreeapps.androidinstantappsdemo.ui.base;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;
import com.willowtreeapps.androidinstantappsdemo.BuildConfig;

/**
 * Created by willowtree on 5/11/17.
 */

public class BaseApplication extends Application {

    static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "application onCreate");
        Log.i(TAG, "version " + BuildConfig.VERSION_NAME + " (" + BuildConfig.VERSION_CODE + ")");
        FirebaseApp.initializeApp(this);
    }

}
