package org.example.service;

import org.example.model.Coupon;

// Service to handle all coupon opertaions.

public class CouponService {

    // Function to validate coupon
    public boolean validate(Coupon coupon, double cartValue) {
        // null coupon check
        if (coupon == null) return false;

        // expired coupon check
        if (coupon.isExpired()) return false;

        // min quantity check
        if (cartValue < coupon.getMinCartValue()) return false;

        return true;
    }

    // Validation with low scope (weak validation)
    public boolean validateCoupon(Coupon coupon) {
        // null coupon check
        if (coupon == null) return false;

        // expired coupon check
        if (coupon.isExpired()) return false;

        return true;
    }

    // Function to calculate discount acc to coupon used.
    public double getDiscount(Coupon coupon, double cartValue) {

        // coupon validation
        if (!validate(coupon, cartValue)) return 0.0;

        switch (coupon.getType()) {
            // flat discount
            case FLAT:
                return Math.min(coupon.getValue(), cartValue);
                // percent discount
            case PERCENT:
                return (cartValue * coupon.getValue()) / 100.0;
                // invalid option chosen
            default:
                return 0.0;
        }
    }
}

