package com.willowtreeapps.androidinstantappsdemo.feature.cart.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.willowtreeapps.androidinstantappsdemo.feature.cart.R;
import com.willowtreeapps.androidinstantappsdemo.util.NavigationUtil;

import java.util.List;
import java.util.Locale;

import static com.willowtreeapps.androidinstantappsdemo.R.string.share_cart;
import static com.willowtreeapps.androidinstantappsdemo.util.Constants.ROOT_ENDPOINT;

/**
 * Created by willowtree on 5/13/17.
 */

public class CartActivity extends AppCompatActivity {

    private CartViewModel viewModel;
    private FloatingActionButton fab;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        viewModel = ViewModelProviders.of(this).get(CartViewModel.class);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setupToolbar();
        handleDeepLink();

        fab.setImageDrawable(VectorDrawableCompat.create(getResources(), R.drawable.ic_share_white_24dp, null));
        fab.setOnClickListener(view -> {
            String cartId = viewModel.getCartId().getValue();
            ShareCompat.IntentBuilder.from(this)
                    .setText(String.format(Locale.US, "Check out my shopping cart now using Android Instant Apps! \n%s/cart/%s", ROOT_ENDPOINT, cartId))
                    .setType("text/plain")
                    .setChooserTitle(share_cart)
                    .startChooser();
        });
    }

    private void handleDeepLink() {
        if (getIntent().getData() != null) {
            List<String> pathSegments = getIntent().getData().getPathSegments();
            if (pathSegments.size() > 1 && pathSegments.get(0).equalsIgnoreCase("cart")) {
                CartActivityFragment fragment = new CartActivityFragment();
                Bundle args = new Bundle();
                args.putString(CartActivityFragment.KEY_CART_ID, pathSegments.get(1));
                fragment.setArguments(args);
                getSupportFragmentManager().beginTransaction().replace(R.id.content, fragment).commit();
            }
        }
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_delete, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            exit();
            return true;
        }
        if (itemId == R.id.delete) {
            viewModel.deleteCart();
            exit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void exit() {
        if (isTaskRoot()) {
            NavigationUtil.goToRoot(this);
            finish();
        } else {
            onBackPressed();
        }
    }

}
