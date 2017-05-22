package com.willowtreeapps.androidinstantappsdemo.data.model;

public class JoinedCartItem extends CartItem {

    private Item fullItem;

    public JoinedCartItem(CartItem cartItem, Item fullItem) {
        super(cartItem);
        this.fullItem = fullItem;
    }

    @Override
    public Item getFullItem() {
        return fullItem;
    }

}