package com.willowtreeapps.instantappsdemo.data.remote;

import android.arch.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

class FirebaseLiveData<T> extends LiveData<T> implements ValueEventListener {

    private final DatabaseReference databaseReference;
    private final Class<? extends T> klass;

    public FirebaseLiveData(DatabaseReference databaseReference, Class<? extends T> klass) {
        this.databaseReference = databaseReference;
        this.klass = klass;
    }

    @Override
    protected void onActive() {
        super.onActive();
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

    }

}
