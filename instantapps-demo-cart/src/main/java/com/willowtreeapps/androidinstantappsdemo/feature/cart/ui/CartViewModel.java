package com.willowtreeapps.androidinstantappsdemo.feature.cart.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.willowtreeapps.androidinstantappsdemo.data.model.Cart;
import com.willowtreeapps.androidinstantappsdemo.data.model.CartItem;
import com.willowtreeapps.androidinstantappsdemo.data.service.Repository;
import com.willowtreeapps.androidinstantappsdemo.feature.cart.injection.DaggerProvider;
import com.willowtreeapps.androidinstantappsdemo.feature.cart.injection.FeatureComponent;

import javax.inject.Inject;

/**
 * Created by willowtree on 5/13/17.
 */

public class CartViewModel extends AndroidViewModel {

    @Inject Repository repository;
    private String cartId;
    private LiveData<Cart> cart;

    public CartViewModel(Application application) {
        this(DaggerProvider.getFeatureComponent(application), application);
    }

    public CartViewModel(FeatureComponent featureComponent, Application application) {
        super(application);
        featureComponent.inject(this);
    }

    public LiveData<String> getCartId() {
        return repository.getCartId();
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
        repository.getCartId().setValue(cartId);
        cart = repository.getCart(cartId);
    }

    public LiveData<Cart> getCart() {
        return cart;
    }

    public void deleteCart() {
        repository.deleteCart();
    }

    public void deleteCartItemAtPosition(int position) {
        CartItem cartItem = getCart().getValue().getItemsCollapsed().get(position);
        // remove this item by posting negative quantity
        repository.insertCartItem(cartId, new CartItem(cartItem.getId(), -cartItem.getQuantity()));
    }

}
