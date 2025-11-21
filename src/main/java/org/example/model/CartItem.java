package org.example.model;

import java.util.*;


public class CartItem {
    private Item item;
    private int quantity;

    public CartItem(Item item, int quantity) {
        if (item == null) throw new IllegalArgumentException("item required");
        this.item = item;
        this.quantity = Math.max(0, quantity);
    }

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

    @Override
    public String toString() {
        return "CartItem{" + item.getCode() + ", qty=" + quantity + "}";
    }
}

