package com.willowtreeapps.androidinstantappsdemo.data.remote;

import android.arch.lifecycle.LiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.willowtreeapps.androidinstantappsdemo.data.model.Cart;
import com.willowtreeapps.androidinstantappsdemo.data.model.Catalog;
import com.willowtreeapps.androidinstantappsdemo.data.model.JoinedCart;

/**
 * Created by willowtree on 5/11/17.
 */
class FirebaseCartLiveData extends LiveData<Cart> {

    private final DatabaseReference cartReference;
    private final DatabaseReference catalogReference;
    private Cart cart;
    private Catalog catalog;

    private final ValueEventListener cartListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            cart = dataSnapshot.getValue(Cart.class);
            onDataChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            onDatabaseError(databaseError);
        }
    };
    private final ValueEventListener catalogListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            catalog = dataSnapshot.getValue(Catalog.class);
            onDataChanged();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {
            onDatabaseError(databaseError);
        }
    };


    public FirebaseCartLiveData(DatabaseReference cartReference, DatabaseReference catalogReference) {
        this.cartReference = cartReference;
        this.catalogReference = catalogReference;
    }

    @Override
    protected void onActive() {
        super.onActive();
        cartReference.addValueEventListener(cartListener);
        catalogReference.addValueEventListener(catalogListener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        cartReference.removeEventListener(cartListener);
        catalogReference.removeEventListener(catalogListener);
    }

    private void onDataChanged() {
        if (cart == null || catalog == null) {
            return; // need both values
        }
        setValue(new JoinedCart(cart, catalog));
    }

    private void onDatabaseError(DatabaseError databaseError) {
    }

}
