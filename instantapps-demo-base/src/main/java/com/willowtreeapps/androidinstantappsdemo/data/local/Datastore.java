package com.willowtreeapps.androidinstantappsdemo.data.local;

import android.arch.lifecycle.MutableLiveData;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;

/**
 * store local data in shared prefs
 */

public class Datastore {

    private static final String TAG = Datastore.class.getSimpleName();
    private static final String PREFS_CART_ID = "PREFS_CART_ID";

    private final SharedPreferences sharedPreferences;
    public final MutableLiveData<String> cartId = new PrefLiveData<>(
            () -> getPrefs().getString(PREFS_CART_ID, null),
            cartId -> getEditor().putString(PREFS_CART_ID, cartId).apply()
    );

    public Datastore(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private SharedPreferences.Editor getEditor() {
        return sharedPreferences.edit();
    }

    private SharedPreferences getPrefs() {
        return sharedPreferences;
    }

    private static class PrefLiveData<T> extends MutableLiveData<T> {
        private final Getter<T> getter;
        private final Setter<T> setter;

        private PrefLiveData(Getter<T> getter, Setter<T> setter) {
            this.getter = getter;
            this.setter = setter;
        }

        @Override
        public void setValue(T value) {
            setter.set(value);
            super.setValue(value);
        }

        @Nullable
        @Override
        public T getValue() {
            return getter.get();
        }

        @Override
        protected void onActive() {
            setValue(getValue());
        }
    }

    private interface Getter<T> {
        T get();
    }

    private interface Setter<T> {
        void set(T t);
    }

}
