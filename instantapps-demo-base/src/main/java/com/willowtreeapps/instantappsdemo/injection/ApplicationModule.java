package com.willowtreeapps.instantappsdemo.injection;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.willowtreeapps.instantappsdemo.data.local.Datastore;
import com.willowtreeapps.instantappsdemo.data.remote.FirebaseDao;
import com.willowtreeapps.instantappsdemo.data.service.Repository;
import com.willowtreeapps.instantappsdemo.util.FirebaseRequestHandler;

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
