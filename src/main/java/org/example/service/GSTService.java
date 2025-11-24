package org.example.service;

import org.example.model.CartItem;

import java.util.Collection;

// Service to implement all tax operations.
public class GSTService {

    // Function to calculate tax per item.
    public double calculateGST(CartItem item) {
        if (item == null) return 0.0;
        double price = item.getTotalPrice();
        return (price * item.getItem().getGstPercent()) / 100.0;
    }

    // Function to return total Cart tax.
    public double calculateTotalGST(Collection<CartItem> items) {
        return items.stream()
                .mapToDouble(this::calculateGST)
                .sum();
    }
}
