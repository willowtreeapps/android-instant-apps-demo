package com.willowtreeapps.androidinstantappsdemo.data.model;

/**
 * Created by willowtree on 5/11/17.
 */

public class CartItem {

    Long id;
    Long quantity;

    public CartItem() {
    }

    public CartItem(CartItem cartItem) {
        this(cartItem.id, cartItem.quantity);
    }

    public CartItem(long id, long quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public long getId() {
        if (id == null) {
            return 0;
        }
        return id;
    }

    public long getQuantity() {
        if (quantity == null) {
            return 1;
        }
        return quantity;
    }

    public Item getFullItem() {
        return null;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", quantity=" + quantity +
                '}';
    }

}
