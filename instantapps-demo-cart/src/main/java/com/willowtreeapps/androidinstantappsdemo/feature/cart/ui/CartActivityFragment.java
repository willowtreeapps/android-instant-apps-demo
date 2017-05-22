package com.willowtreeapps.androidinstantappsdemo.feature.cart.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.willowtreeapps.androidinstantappsdemo.data.model.Cart;
import com.willowtreeapps.androidinstantappsdemo.data.model.CartItem;
import com.willowtreeapps.androidinstantappsdemo.feature.cart.R;
import com.willowtreeapps.androidinstantappsdemo.ui.base.InstantAppsLifecycleFragment;

import static com.willowtreeapps.androidinstantappsdemo.R.string.total_price_n;
import static com.willowtreeapps.androidinstantappsdemo.util.Constants.MONEY_FORMAT;

/**
 * A placeholder fragment containing a simple view.
 */
public class CartActivityFragment extends InstantAppsLifecycleFragment {

    private static final String TAG = CartActivityFragment.class.getSimpleName();
    public static final String KEY_CART_ID = "KEY_CART_ID";

    private CartViewModel viewModel;
    private TextView totalPrice;
    private RecyclerView recyclerView;
    private CartItemAdapter adapter;

    private final ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.START | ItemTouchHelper.END) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
                    return false; // don't care
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                    viewModel.deleteCartItemAtPosition(viewHolder.getAdapterPosition());
                }
            }
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = ViewModelProviders.of(getActivity()).get(CartViewModel.class);
        viewModel.setCartId(getArguments().getString(KEY_CART_ID));

        totalPrice = (TextView) view.findViewById(R.id.total_price);
        recyclerView = (RecyclerView) view.findViewById(R.id.cart_items);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new CartItemAdapter();
        adapter.setHasStableIds(true);
        recyclerView.setAdapter(adapter);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        viewModel.getCart().observe(this, this::updateForCart);
    }

    private void updateForCart(Cart cart) {
        Log.i(TAG, cart.getItemsCollapsed().toString());
        adapter.setData(cart.getItemsCollapsed());
        int priceSum = 0;
        for (CartItem cartItem : cart.getItemsCollapsed()) {
            if (cartItem.getFullItem() == null) {
                continue;
            }
            priceSum += cartItem.getQuantity() * cartItem.getFullItem().getPrice();
        }
        totalPrice.setText(getResources().getString(total_price_n, MONEY_FORMAT.format(priceSum)));
    }

}
