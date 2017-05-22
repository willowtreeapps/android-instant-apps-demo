package com.willowtreeapps.androidinstantappsdemo.data.service;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.willowtreeapps.androidinstantappsdemo.data.local.Datastore;
import com.willowtreeapps.androidinstantappsdemo.data.model.Cart;
import com.willowtreeapps.androidinstantappsdemo.data.model.CartItem;
import com.willowtreeapps.androidinstantappsdemo.data.model.Catalog;
import com.willowtreeapps.androidinstantappsdemo.data.model.Item;
import com.willowtreeapps.androidinstantappsdemo.data.remote.FirebaseDao;

public class Repository {

    private FirebaseDao firebaseDao;
    private Datastore datastore;

    public Repository(FirebaseDao firebaseDao, Datastore datastore) {
        this.firebaseDao = firebaseDao;
        this.datastore = datastore;
    }

    public LiveData<Catalog> getCatalog() {
        return firebaseDao.getCatalog();
    }

    public LiveData<Cart> getCart(String cartId) {
        return firebaseDao.getCart(cartId);
    }

    public LiveData<Item> getItem(long itemId) {
        return firebaseDao.getItem(itemId);
    }

    public void insertCartItem(String cartId, CartItem cartItem) {
        firebaseDao.insertCartItem(cartId, cartItem);
    }

    /**
     * creates a new cart and returns the cart id
     */
    public String insertCart() {
        String newCartId = firebaseDao.insertCart();
        getCartId().setValue(newCartId);
        return newCartId;
    }

    public void deleteCart() {
        firebaseDao.deleteCart(getCartId().getValue());
        getCartId().setValue(null);
    }

    /**
     * user's current cart id
     */
    public MutableLiveData<String> getCartId() {
        return datastore.cartId;
    }

}
