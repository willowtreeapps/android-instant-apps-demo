package com.willowtreeapps.androidinstantappsdemo.feature.itemdetail.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.willowtreeapps.androidinstantappsdemo.feature.itemdetail.R;
import com.willowtreeapps.androidinstantappsdemo.ui.base.InstantAppsLifecycleActivity;
import com.willowtreeapps.androidinstantappsdemo.util.NavigationUtil;

import java.util.List;

import static com.willowtreeapps.androidinstantappsdemo.R.id.my_cart;
import static com.willowtreeapps.androidinstantappsdemo.R.menu.menu_cart;
import static com.willowtreeapps.androidinstantappsdemo.R.string.item_added;
import static com.willowtreeapps.androidinstantappsdemo.R.string.view_cart;

public class ItemDetailActivity extends InstantAppsLifecycleActivity {

    private ItemDetailViewModel viewModel;
    private MenuItem cartMenuItem;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);
        viewModel = ViewModelProviders.of(this).get(ItemDetailViewModel.class);
        viewModel.getCartId().observe(this, this::updatedCartId);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        handleDeepLink();
        setupToolbar();

        fab.setImageDrawable(VectorDrawableCompat.create(getResources(), R.drawable.ic_add_shopping_cart_white_24dp, null));
        fab.setOnClickListener(this::addToCart);
    }

    private void handleDeepLink() {
        Log.e("ItemDetailActivity", "intent: " + getIntent().toString());
        Log.e("ItemDetailActivity", "intent data: " + getIntent().getDataString());
        if (getIntent().getData() != null) {
            List<String> pathSegments = getIntent().getData().getPathSegments();
            if (pathSegments.size() > 1 && pathSegments.get(0).equalsIgnoreCase("item")) {
                ItemDetailActivityFragment fragment = new ItemDetailActivityFragment();
                Bundle args = new Bundle();
                long itemId = Long.parseLong(pathSegments.get(1));
                Log.e("ItemDetailActivity", "itemId: " + itemId);
                viewModel.setItemId(itemId);
                args.putLong(ItemDetailActivityFragment.KEY_ITEM_ID, itemId);
                fragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
            }
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        viewModel.getItem().observe(this, item -> toolbar.setTitle(item.getName()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(menu_cart, menu);
        cartMenuItem = menu.findItem(my_cart);
        updatedCartId(viewModel.getCartId().getValue());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            if (isTaskRoot()) {
                NavigationUtil.goToRoot(this);
                finish();
            } else {
                onBackPressed();
            }
            return true;
        }
        if (itemId == my_cart) {
            viewCart(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updatedCartId(String cartId) {
        if (cartMenuItem != null) {
            cartMenuItem.setVisible(!TextUtils.isEmpty(cartId));
        }
    }

    private void addToCart(Object ignore) {
        viewModel.addItem(1);
        Snackbar.make(fab, item_added, Snackbar.LENGTH_LONG)
                .setAction(view_cart, this::viewCart).show();
    }

    private void viewCart(Object ignore) {
        String cartId = viewModel.getCartId().getValue();
        if (!TextUtils.isEmpty(cartId)) {
            NavigationUtil.goToCart(this, cartId);
        }
    }

}
