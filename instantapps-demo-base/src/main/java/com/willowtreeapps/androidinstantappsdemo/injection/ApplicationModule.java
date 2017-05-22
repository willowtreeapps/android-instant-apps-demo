package com.willowtreeapps.androidinstantappsdemo.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.willowtreeapps.androidinstantappsdemo.data.local.Datastore;
import com.willowtreeapps.androidinstantappsdemo.data.remote.FirebaseDao;
import com.willowtreeapps.androidinstantappsdemo.data.service.Repository;
import com.willowtreeapps.androidinstantappsdemo.util.FirebaseRequestHandler;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    public static final String FIREBASE_STORAGE_PICASSO = "FirebaseStorage";

    @Provides
    @NonNull
    @Singleton
    public Gson provideGson() {
        return new Gson();
    }

    @Provides
    @NonNull
    @Singleton
    public SharedPreferences provideSharedPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @NonNull
    @Singleton
    public Datastore provideDatastore(SharedPreferences sharedPreferences) {
        return new Datastore(sharedPreferences);
    }

    @Provides
    @NonNull
    @Singleton
    public Repository provideRepository(FirebaseDao firebaseDao, Datastore datastore) {
        return new Repository(firebaseDao, datastore);
    }

    @Provides
    @NonNull
    @Singleton
    public FirebaseDao provideFirebaseDao() {
        return new FirebaseDao();
    }

    @Provides
    @NonNull
    @Singleton
    @Named(FIREBASE_STORAGE_PICASSO)
    public Picasso provideFirebasePicasso(Context context) {
        return new Picasso.Builder(context)
                .addRequestHandler(new FirebaseRequestHandler())
                .build();
    }

}
