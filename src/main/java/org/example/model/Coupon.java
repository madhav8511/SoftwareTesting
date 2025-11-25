package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

// class for coupon objects
public class Coupon {

    // object fields
    // enum for coupon discount types
    public enum Type { FLAT, PERCENT }

    // unique identifer string code
    private String code;
    // enum type
    private Type type;
    // coupon value
    private double value;
    // min cart to apply
    private double minCartValue;
    // set expiry date using LocalDate
    private LocalDate expiry;

    //constructor
    public Coupon(String code, Type type, double value, double minCartValue, LocalDate expiry) {
        // empty code check
        // null code check
        if (code == null || code.isEmpty()) throw new IllegalArgumentException("coupon code required");
        Objects.requireNonNull(type);
        this.code = code;
        this.type = type;
        this.value = value;
        this.minCartValue = minCartValue;
        this.expiry = expiry;
    }

    //Getter and setter
    public String getCode() {
        return code;
    }

    public Type getType() {
        return type;
    }

    public double getValue() {
        return value;
    }

    public double getMinCartValue() {
        return minCartValue;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setMinCartValue(double minCartValue) {
        this.minCartValue = minCartValue;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCode(String code) {
        // empty code check
        // null code check
        if (code == null || code.isEmpty()) throw new IllegalArgumentException("coupon code required");
        this.code = code;
    }

    // Validity check
    public boolean isExpired() {
        // null expiry check
        if (expiry == null) return false;
        // using inbuilt methods of LocalDate
        return expiry.isBefore(LocalDate.now());
    }

    // overriding function to display
    @Override
    public String toString() {

        return "Coupon{" + code + ", " + type + ", val=" + value + ", min=" + minCartValue + ", expiry=" + expiry + "}";
    }
}

