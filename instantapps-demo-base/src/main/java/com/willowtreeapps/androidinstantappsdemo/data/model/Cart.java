package com.willowtreeapps.androidinstantappsdemo.data.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by willowtree on 5/11/17.
 */

public class Cart {

    Map<String, CartItem> items;

    public Cart() {
    }

    public Cart(Cart cart) {
        items = cart.items;
    }

    public Map<String, CartItem> getItems() {
        return items;
    }

    public List<CartItem> getItemsCollapsed() {
        Map<Long, CartItem> items = new TreeMap<>();
        for (CartItem cartItem : getItems().values()) {
            CartItem item = items.get(cartItem.getId());
            if (item != null) { // update item
                item.quantity = item.getQuantity() + cartItem.getQuantity();
            } else { // new item
                items.put(cartItem.getId(), cartItem);
            }
        }
        return new ArrayList<>(items.values());
    }

}
