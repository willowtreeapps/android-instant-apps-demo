package com.willowtreeapps.androidinstantappsdemo.data.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by willowtree on 5/14/17.
 */

public class JoinedCart extends Cart {

    private final List<CartItem> cartItems;

    public JoinedCart(Cart cart, Catalog catalog) {
        super(cart);
        Map<Long, CartItem> items = new TreeMap<>();
        Map<String, CartItem> rawItems = getItems();
        List<String> rawKeys = new ArrayList<>(rawItems.keySet());
        Collections.sort(rawKeys);
        for (String key : rawKeys) {
            CartItem cartItem = rawItems.get(key);
            CartItem item = items.get(cartItem.getId());
            if (item != null) { // update item
                item.quantity = Math.max(item.getQuantity() + cartItem.getQuantity(), 0);
            } else { // new item
                if (cartItem.getId() < catalog.getItems().size()) {
                    // can join with catalog
                    JoinedCartItem fullItem = new JoinedCartItem(cartItem, catalog.getItems().get((int) cartItem.getId()));
                    items.put(cartItem.getId(), fullItem);
                } else {
                    // item not in catalog. just insert skeleton item
                    items.put(cartItem.getId(), cartItem);
                }
            }
        }
        // remove zero quantity items
        Iterator<Map.Entry<Long, CartItem>> iter = items.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Long, CartItem> entry = iter.next();
            if (entry.getValue().getQuantity() < 1) {
                iter.remove();
            }
        }
        this.cartItems = new ArrayList<>(items.values());
    }

    @Override
    public List<CartItem> getItemsCollapsed() {
        return cartItems;
    }

}
