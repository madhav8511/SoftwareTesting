package org.example.model;

import java.util.*;

// Class for cartitems object
public class CartItem {

    //private fields
    private Item item;
    private int quantity;

    // constructor
    public CartItem(Item item, int quantity) {
        if (item == null) throw new IllegalArgumentException("item required");
        this.item = item;
        this.quantity = Math.max(0, quantity);
    }

    //Getter and setters
    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setItem(Item item) {
        if (item == null) throw new IllegalArgumentException("item required");
        this.item = item;
    }

    public void setQuantity(int quantity) {
        this.quantity = Math.max(0, quantity);
    }

    // Methods for quantity management
    public void increment(int delta) {
        if (delta <= 0) return;
        this.quantity += delta;
    }

    public void decrement(int delta) {
        if (delta <= 0) return;
        this.quantity = Math.max(0, this.quantity - delta);
    }

    public double getTotalPrice() {
        return item.getPrice() * quantity;
    }

    //Overriding function to return display
    @Override
    public String toString() {
        return "CartItem{" + item.getCode() + ", qty=" + quantity + "}";
    }
}

