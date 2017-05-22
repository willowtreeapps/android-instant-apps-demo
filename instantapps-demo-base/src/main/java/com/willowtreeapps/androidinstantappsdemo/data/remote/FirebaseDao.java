package com.willowtreeapps.androidinstantappsdemo.data.remote;

import android.arch.lifecycle.LiveData;

import com.google.firebase.database.FirebaseDatabase;
import com.willowtreeapps.androidinstantappsdemo.data.model.Cart;
import com.willowtreeapps.androidinstantappsdemo.data.model.CartItem;
import com.willowtreeapps.androidinstantappsdemo.data.model.Catalog;
import com.willowtreeapps.androidinstantappsdemo.data.model.Item;

/**
 * Created by willowtree on 5/11/17.
 */

public class FirebaseDao {

    private final FirebaseDatabase firebaseDatabase;

    public FirebaseDao() {
        firebaseDatabase = FirebaseDatabase.getInstance();
    }

    public LiveData<Catalog> getCatalog() {
        return new FirebaseLiveData<>(firebaseDatabase.getReference("/catalog"), Catalog.class);
    }

    public LiveData<Cart> getCart(String cartId) {
        return new FirebaseCartLiveData(
                firebaseDatabase.getReference("/orders/" + cartId),
                firebaseDatabase.getReference("/catalog")
        );
    }

    public LiveData<Item> getItem(long itemId) {
        return new FirebaseLiveData<>(firebaseDatabase.getReference("/catalog/items/" + itemId), Item.class);
    }

    public void insertCartItem(String cartId, CartItem cartItem) {
        firebaseDatabase.getReference("/orders/" + cartId + "/items").push().setValue(cartItem);
    }

    public String insertCart() {
        return firebaseDatabase.getReference("/orders").push().getKey();
    }

    public void deleteCart(String cartId) {
        firebaseDatabase.getReference("/orders/" + cartId).setValue(null);
    }

}
