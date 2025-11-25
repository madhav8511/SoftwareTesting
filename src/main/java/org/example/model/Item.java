package org.example.model;

// Start of the Item class definition.
// This class holds item data.
public class Item {

    // --- Private Fields ---

    // Unique identifier for the item.
    private String code;

    // Display name of the item.
    private String name;

    // Base price of the item.
    private double price;

    // Applicable tax percentage (GST).
    private double gstPercent;

    // --- Constructor ---

    // Constructor to create a new Item object.
    // It accepts four initial values.
    public Item(String code, String name, double price, double gstPercent) {
        // Validation check for the 'code'.
        // Code must not be null or empty.
        if (code == null || code.isEmpty()) {
            // Throw an error if the code is invalid.
            throw new IllegalArgumentException("code required");
        }
        // Set the internal code field.
        this.code = code;
        // Set the internal name field.
        this.name = name;
        // Set the internal price field.
        this.price = price;
        // Set the internal GST percentage.
        this.gstPercent = gstPercent;
    }

    // --- Getters ---

    // Getter method for the item code.
    // Returns the String code.
    public String getCode() {
        return code;
    }

    // Getter method for the item name.
    // Returns the String name.
    public String getName() {
        return name;
    }

    // Getter method for the price.
    // Returns the double price value.
    public double getPrice() {
        return price;
    }

    // Getter method for the GST percentage.
    // Returns the double gstPercent value.
    public double getGstPercent() {
        return gstPercent;
    }

    // --- Setters ---

    // Setter method to update the item code.
    public void setCode(String code) {
        // Re-validate the new code value.
        // It must be provided and not empty.
        if (code == null || code.isEmpty()) {
            // Error on invalid code update.
            throw new IllegalArgumentException("code required");
        }
        // Update the item's code.
        this.code = code;
    }

    // Setter method to update the item name.
    public void setName(String name) {
        // Note: Name can be null or empty here.
        this.name = name;
    }

    // Setter method to update the price.
    public void setPrice(double price) {
        // Update the item's price.
        this.price = price;
    }

    // Setter method to update the GST percentage.
    public void setGstPercent(double gstPercent) {
        // Update the item's GST rate.
        this.gstPercent = gstPercent;
    }

    // --- Utility ---

    // Override the default toString method.
    // Provides a readable string representation of the object.
    @Override
    public String toString() {
        // Format and return the item details as a string.
        return "Item{" + code + ", " + name + ", price=" + price + ", gst=" + gstPercent + "}";
    }
}