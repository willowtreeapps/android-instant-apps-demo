package com.willowtreeapps.instantappsdemo.feature.browse.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.willowtreeapps.instantappsdemo.data.model.Catalog;
import com.willowtreeapps.instantappsdemo.data.service.Repository;
import com.willowtreeapps.instantappsdemo.feature.browse.injection.DaggerProvider;
import com.willowtreeapps.instantappsdemo.feature.browse.injection.FeatureComponent;

import javax.inject.Inject;

public class CatalogViewModel extends AndroidViewModel {

    @Inject Repository repository;

    private long itemId;

    public CatalogViewModel(Application application) {
        this(DaggerProvider.getFeatureComponent(application), application);
    }

    public CatalogViewModel(FeatureComponent featureComponent, Application application) {
        super(application);
        featureComponent.inject(this);
    }

    public LiveData<Catalog> getCatalog() {
        return repository.getCatalog();
    }


}
