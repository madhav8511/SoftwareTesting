package org.example.model;

// Import all utility classes from java.util.
import java.util.*;

// ---

// This class represents a single entry in a shopping cart.
// It tracks a specific item and how many the customer wants.
public class CartItem {

    // --- Private Fields ---

    // The actual product being held in the cart entry.
    // This is an object of the previously defined 'Item' class.
    private Item item;

    // The count of how many units of the item are in the cart.
    // This is an integer value.
    private int quantity;

    // --- Constructor ---

    // Constructor to create a new cart entry.
    // Requires an Item object and a starting quantity.
    public CartItem(Item item, int quantity) {
        // Essential validation check for the Item object.
        // An item cannot be null.
        if (item == null) {
            // Throw an exception if no Item is provided.
            throw new IllegalArgumentException("item required");
        }

        // Assign the Item object to the private field.
        this.item = item;
        // Assign the quantity, ensuring it's at least zero.
        // Math.max(0, quantity) prevents negative starting quantity.
        this.quantity = Math.max(0, quantity);
    }

    // --- Getters ---

    // Getter method to retrieve the stored Item object.
    // Returns the product details.
    public Item getItem() {
        return item;
    }

    // Getter method to retrieve the current quantity.
    // Returns the integer count.
    public int getQuantity() {
        return quantity;
    }

    // --- Setters ---

    // Setter method to replace the Item object.
    public void setItem(Item item) {
        // Must validate the new Item object as well.
        if (item == null) {
            // Throw an error if the replacement item is null.
            throw new IllegalArgumentException("item required");
        }
        // Update the item reference.
        this.item = item;
    }

    // Setter method to set a specific quantity value.
    public void setQuantity(int quantity) {
        // Assign the new quantity, ensuring it is not negative.
        // The quantity will be zero if a negative number is passed in.
        this.quantity = Math.max(0, quantity);
    }

    // --- Modifier Methods ---

    // Method to increase the quantity by a given amount.
    // The delta parameter is the amount to add.
    public void increment(int delta) {
        // Check if the change amount is positive.
        if (delta <= 0) {
            // Do nothing if the delta is not positive.
            return;
        }
        // Increase the current quantity by the delta.
        this.quantity += delta;
    }

    // Method to decrease the quantity by a given amount.
    // The delta parameter is the amount to subtract.
    public void decrement(int delta) {
        // Check if the change amount is positive.
        if (delta <= 0) {
            // Do nothing if the delta is not positive.
            return;
        }
        // Decrease the quantity, but ensure it doesn't drop below zero.
        // Math.max(0, ...) ensures the quantity remains non-negative.
        this.quantity = Math.max(0, this.quantity - delta);
    }

    // --- Calculation Method ---

    // Method to calculate the total cost for this cart entry.
    // This returns the unit price multiplied by the quantity.
    public double getTotalPrice() {
        // Get the single item's price.
        // Multiply the price by the number of units.
        return item.getPrice() * quantity;
    }

    // --- Utility Method (toString) ---

    // Overrides the default toString() method.
    // Provides a quick string representation for debugging/logging.
    @Override
    public String toString() {
        // Return a formatted string showing the item code and the quantity.
        return "CartItem{" + item.getCode() + ", qty=" + quantity + "}";
    }
}