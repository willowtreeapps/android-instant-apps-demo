package com.willowtreeapps.instantappsdemo.ui.base;

import android.app.Application;
import android.util.Log;

import com.google.firebase.FirebaseApp;

/**
 * Created by willowtree on 5/11/17.
 */

public class BaseApplication extends Application {

    static final String TAG = BaseApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "application onCreate");
        FirebaseApp.initializeApp(this);
    }

}
