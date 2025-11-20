package org.example.model;

import java.time.LocalDate;
import java.util.Objects;

public class Coupon {
    public enum Type { FLAT, PERCENT }
    private String code;
    private Type type;
    private double value;
    private double minCartValue;
    private LocalDate expiry;

    public Coupon(String code, Type type, double value, double minCartValue, LocalDate expiry) {
        if (code == null || code.isEmpty()) throw new IllegalArgumentException("coupon code required");
        Objects.requireNonNull(type);
        this.code = code;
        this.type = type;
        this.value = value;
        this.minCartValue = minCartValue;
        this.expiry = expiry;
    }

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
        if (code == null || code.isEmpty()) throw new IllegalArgumentException("coupon code required");
        this.code = code;
    }

    public boolean isExpired() {
        if (expiry == null) return false;
        return expiry.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Coupon{" + code + ", " + type + ", val=" + value + ", min=" + minCartValue + ", expiry=" + expiry + "}";
    }
}

