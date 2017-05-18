package com.willowtreeapps.instantappsdemo.feature.browse.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.willowtreeapps.instantappsdemo.data.service.Repository;
import com.willowtreeapps.instantappsdemo.feature.browse.R;
import com.willowtreeapps.instantappsdemo.feature.browse.injection.DaggerProvider;
import com.willowtreeapps.instantappsdemo.ui.base.AppCompatLifecycleActivity;

import javax.inject.Inject;

public class BrowseActivity extends AppCompatLifecycleActivity {

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
        RecyclerView browseRecyclerView = (RecyclerView) findViewById(R.id.browseRecyclerView);
        RecyclerView.LayoutManager recyclerViewLayoutManager = new GridLayoutManager(this, columnCount);
        browseRecyclerView.setLayoutManager(recyclerViewLayoutManager);

        adapter = new CatalogRecyclerAdapter(this, repository.getCatalog().getValue());
        browseRecyclerView.setAdapter(adapter);
        viewModel.getCatalog().observe(this, adapter::setCatalog);
    }

}
