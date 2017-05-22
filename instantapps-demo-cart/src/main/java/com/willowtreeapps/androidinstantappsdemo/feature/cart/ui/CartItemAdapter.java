package com.willowtreeapps.androidinstantappsdemo.feature.cart.ui;

import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.willowtreeapps.androidinstantappsdemo.data.model.CartItem;
import com.willowtreeapps.androidinstantappsdemo.data.model.Item;
import com.willowtreeapps.androidinstantappsdemo.feature.cart.R;
import com.willowtreeapps.androidinstantappsdemo.util.NavigationUtil;

import java.util.ArrayList;
import java.util.List;

import static com.willowtreeapps.androidinstantappsdemo.R.string.item_n;
import static com.willowtreeapps.androidinstantappsdemo.R.string.quantity_n;
import static com.willowtreeapps.androidinstantappsdemo.util.Constants.MONEY_FORMAT;

/**
 * Created by willowtree on 5/14/17.
 */

public class CartItemAdapter extends RecyclerView.Adapter<CartItemAdapter.ViewHolder> {

    private final List<CartItem> cartItems = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_item_cart_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.bind(cartItems.get(i));
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    @Override
    public long getItemId(int position) {
        return cartItems.get(position).getId();
    }

    public void setData(List<CartItem> cartItems) {
        this.cartItems.clear();
        this.cartItems.addAll(cartItems);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView name;
        private final TextView price;
        private final TextView quantity;
        private CartItem cartItem;

        ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            price = (TextView) itemView.findViewById(R.id.price);
            quantity = (TextView) itemView.findViewById(R.id.quantity);
            itemView.setOnClickListener(this);
        }

        void bind(CartItem cartItem) {
            this.cartItem = cartItem;
            Resources res = itemView.getResources();
            quantity.setText(res.getString(quantity_n, cartItem.getQuantity()));
            Item fullItem = cartItem.getFullItem();
            if (fullItem == null) {
                // full info not available
                name.setText(res.getString(item_n, cartItem.getId()));
                return;
            }
            name.setText(fullItem.getName());
            price.setText(MONEY_FORMAT.format(fullItem.getPrice()));
        }

        @Override
        public void onClick(View v) {
            NavigationUtil.goToItem(itemView.getContext(), cartItem.getId());
        }

    }

}
