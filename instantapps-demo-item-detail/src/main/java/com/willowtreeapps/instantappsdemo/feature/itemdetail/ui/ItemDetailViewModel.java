package com.willowtreeapps.instantappsdemo.feature.itemdetail.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.text.TextUtils;

import com.willowtreeapps.instantappsdemo.data.model.CartItem;
import com.willowtreeapps.instantappsdemo.data.model.Item;
import com.willowtreeapps.instantappsdemo.data.service.Repository;
import com.willowtreeapps.instantappsdemo.feature.itemdetail.injection.DaggerProvider;
import com.willowtreeapps.instantappsdemo.feature.itemdetail.injection.FeatureComponent;

import javax.inject.Inject;

/**
 * Created by willowtree on 5/13/17.
 */

public class ItemDetailViewModel extends AndroidViewModel {

    @Inject Repository repository;
    private long itemId;

    public ItemDetailViewModel(Application application) {
        this(DaggerProvider.getFeatureComponent(application), application);
    }

    public ItemDetailViewModel(FeatureComponent featureComponent, Application application) {
        super(application);
        featureComponent.inject(this);
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public LiveData<Item> getItem() {
        return repository.getItem(itemId);
    }

    public LiveData<String> getCartId() {
        return repository.getCartId();
    }

    public void addItem(int quantity) {
        String cartId = getCartId().getValue();
        if (TextUtils.isEmpty(cartId)) {
            cartId = repository.insertCart();
        }
        repository.insertCartItem(cartId, new CartItem(itemId, quantity));
    }

}
