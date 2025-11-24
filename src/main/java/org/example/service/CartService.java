package org.example.service;

import org.example.model.CartItem;
import org.example.model.Item;

import java.util.HashMap;
import java.util.Map;

// Cart Service to handle cart operations.

public class CartService {

    // Store items with code
    private final Map<String, CartItem> cart = new HashMap<>();

    // Fucntion to add item to cart
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

    //Function to reduce item
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

    // Function to remove item completely
    public void removeItem(String code) {
        cart.remove(code);
    }

    // function to get total cart value
    public double getSubtotal() {
        return cart.values()
                .stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

    // Function to return whole cart map
    public Map<String, CartItem> getItems() {
        return cart;
    }

    // Remove all items from cart
    public void clear() {
        cart.clear();
    }
}

