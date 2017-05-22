package com.willowtreeapps.androidinstantappsdemo.feature.browse.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.willowtreeapps.androidinstantappsdemo.data.model.Catalog;
import com.willowtreeapps.androidinstantappsdemo.data.service.Repository;
import com.willowtreeapps.androidinstantappsdemo.feature.browse.injection.DaggerProvider;
import com.willowtreeapps.androidinstantappsdemo.feature.browse.injection.FeatureComponent;

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
