package com.willowtreeapps.androidinstantappsdemo.data.model;

/**
 * Created by willowtree on 5/11/17.
 */

public class Item {

    Long id;
    String name;
    String description;
    String image;
    Integer price;

    public long getId() {
        if (id == null) {
            return 0;
        }
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    /**
     * relative file path to the image in Firebase Storage
     */
    public String getImage() {
        return image;
    }

    /**
     * price in dollars
     */
    public int getPrice() {
        if (price == null) {
            return 0;
        }
        return price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", price=" + price +
                '}';
    }
}
