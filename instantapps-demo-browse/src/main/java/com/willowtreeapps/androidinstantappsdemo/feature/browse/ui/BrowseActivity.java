package com.willowtreeapps.androidinstantappsdemo.feature.browse.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.willowtreeapps.androidinstantappsdemo.data.service.Repository;
import com.willowtreeapps.androidinstantappsdemo.feature.browse.R;
import com.willowtreeapps.androidinstantappsdemo.feature.browse.injection.DaggerProvider;
import com.willowtreeapps.androidinstantappsdemo.ui.base.InstantAppsLifecycleActivity;

import javax.inject.Inject;

public class BrowseActivity extends InstantAppsLifecycleActivity {

    private static final String TAG = BrowseActivity.class.getSimpleName();

    @Inject
    Repository repository;

    CatalogRecyclerAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        DaggerProvider.getFeatureComponent(getApplicationContext()).inject(this);

        CatalogViewModel viewModel = ViewModelProviders.of(this).get(CatalogViewModel.class);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int columnCount = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2;
        Log.e(TAG, "cols = " + columnCount);
        RecyclerView browseRecyclerView = (RecyclerView) findViewById(R.id.browseRecyclerView);
        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(this, columnCount);
        browseRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        adapter = new CatalogRecyclerAdapter(this, repository.getCatalog().getValue());
        browseRecyclerView.setAdapter(adapter);
        viewModel.getCatalog().observe(this, adapter::setCatalog);
    }

}
