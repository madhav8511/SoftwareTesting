package org.example.model;

// Import the date class from Java's time API.
import java.time.LocalDate;

// Import the utility class for object operations.
import java.util.Objects;

// ---

// This is the main class definition for a promotional coupon.
// It holds all the rules and attributes of a discount coupon.
public class Coupon {

    // These constants define the two kinds of discounts.
    public enum Type {
        // The coupon provides a fixed monetary reduction (e.g., $10 off).
        FLAT,
        // The coupon provides a percentage reduction (e.g., 20% off).
        PERCENT
    }

    // --- Private Fields ---

    // The unique string code of the coupon (e.g., "SUMMER20").
    private String code;

    // The type of the discount, using the defined 'Type' enum.
    private Type type;

    // The discount amount (e.g., 10.0 for $10 or 20.0 for 20%).
    private double value;

    // The minimum purchase amount required to use the coupon.
    private double minCartValue;

    // The date when the coupon is no longer valid.
    private LocalDate expiry;

    // --- Constructor ---

    // Constructor to initialize a new Coupon object.
    // It takes all necessary fields as parameters.
    public Coupon(String code, Type type, double value, double minCartValue, LocalDate expiry) {
        // Validation check for the coupon code.
        // The code cannot be null or an empty string.
        if (code == null || code.isEmpty()) {
            // Throw an exception if the required code is missing.
            throw new IllegalArgumentException("coupon code required");
        }
        // Use the Objects utility to ensure the Type is not null.
        Objects.requireNonNull(type);

        // Assign the provided code to the instance variable.
        this.code = code;
        // Assign the provided type to the instance variable.
        this.type = type;
        // Assign the provided discount value.
        this.value = value;
        // Assign the minimum required cart value.
        this.minCartValue = minCartValue;
        // Assign the expiration date.
        this.expiry = expiry;
    }

    // --- Getter Methods (Accessors) ---

    // Getter for the unique coupon code.
    // Returns the code as a String.
    public String getCode() {
        return code;
    }

    // Getter for the coupon type (FLAT or PERCENT).
    // Returns the Type enum value.
    public Type getType() {
        return type;
    }

    // Getter for the discount value.
    // Returns the double value.
    public double getValue() {
        return value;
    }

    // Getter for the minimum required cart value.
    // Returns the double minimum value.
    public double getMinCartValue() {
        return minCartValue;
    }

    // Getter for the expiration date.
    // Returns the LocalDate object.
    public LocalDate getExpiry() {
        return expiry;
    }

    // --- Setter Methods (Mutators) ---

    // Setter to update the expiration date.
    public void setExpiry(LocalDate expiry) {
        // Assign the new expiration date.
        this.expiry = expiry;
    }

    // Setter to update the discount value.
    public void setValue(double value) {
        // Assign the new discount value.
        this.value = value;
    }

    // Setter to update the minimum cart value requirement.
    public void setMinCartValue(double minCartValue) {
        // Assign the new minimum cart value.
        this.minCartValue = minCartValue;
    }

    // Setter to update the coupon type.
    public void setType(Type type) {
        // Assign the new coupon type.
        this.type = type;
    }

    // Setter to update the coupon code.
    public void setCode(String code) {
        // Re-validate the new code value.
        if (code == null || code.isEmpty()) {
            // Throw an error if the new code is invalid.
            throw new IllegalArgumentException("coupon code required");
        }
        // Update the item's code field.
        this.code = code;
    }

    // --- Business Logic Method ---

    // Method to check if the coupon has expired.
    // Returns a boolean: true if expired, false otherwise.
    public boolean isExpired() {
        // Check if the expiry date is even set (optional).
        if (expiry == null) {
            // If there's no expiry date, it's considered non-expired.
            return false;
        }
        // Compare the expiry date to today's date.
        // Returns true if expiry date is before the current date.
        return expiry.isBefore(LocalDate.now());
    }

    // --- Utility Method (toString) ---

    // Override the base Java object's toString method.
    // Used for printing and logging the object's state.
    @Override
    public String toString() {
        // Construct and return a formatted string with all key attributes.
        return "Coupon{" + code + ", " + type + ", val=" + value + ", min=" + minCartValue + ", expiry=" + expiry + "}";
    }
}