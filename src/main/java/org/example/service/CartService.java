package org.example.service;

import org.example.model.CartItem;
import org.example.model.Item;

import java.util.HashMap;
import java.util.Map;

public class CartService {

    private final Map<String, CartItem> cart = new HashMap<>();

    public void addItem(Item item, int qty) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");

        CartItem existing = cart.get(item.getCode());
        if (existing != null) {
            existing.increment(qty);
        } else {
            cart.put(item.getCode(), new CartItem(item, qty));
        }
    }

    public void reduceItem(Item item, int qty) {
        if (item == null) throw new IllegalArgumentException("Item cannot be null");
        if (qty <= 0) throw new IllegalArgumentException("Quantity must be > 0");

        CartItem existing = cart.get(item.getCode());
        if (existing == null) return;

        existing.decrement(qty);

        if (existing.getQuantity() == 0) {
            cart.remove(item.getCode());
        }
    }

    public void removeItem(String code) {
        cart.remove(code);
    }


    public double getSubtotal() {
        return cart.values()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    public Map<String, CartItem> getItems() {
        return cart;
    }

    public void clear() {
        cart.clear();
    }
}

