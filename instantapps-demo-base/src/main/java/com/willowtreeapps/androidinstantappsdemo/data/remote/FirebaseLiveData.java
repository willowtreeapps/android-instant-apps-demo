package com.willowtreeapps.androidinstantappsdemo.data.remote;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

class FirebaseLiveData<T> extends LiveData<T> implements ValueEventListener {

    private static final String TAG = FirebaseLiveData.class.getSimpleName();

    private final DatabaseReference databaseReference;
    private final Class<? extends T> klass;

    public FirebaseLiveData(DatabaseReference databaseReference, Class<? extends T> klass) {
        this.databaseReference = databaseReference;
        this.klass = klass;
        Log.i(TAG, "new FirebaseLiveData " + klass.getSimpleName() + "\n" + databaseReference.toString());
    }

    @Override
    protected void onActive() {
        super.onActive();
        Log.i(TAG, "onActive " + klass.getSimpleName());
        databaseReference.addValueEventListener(this);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.removeEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        setValue(dataSnapshot.getValue(klass));
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.e(TAG, "onCancelled", databaseError.toException());
        Log.e(TAG, "message: " + databaseError.getMessage());
        Log.e(TAG, "details: " + databaseError.getDetails());
    }

    @Override
    public void observe(LifecycleOwner owner, Observer<T> observer) {
        super.observe(owner, observer);
    }

}
