package org.example.service;

import org.example.model.Coupon;

// Service to handle all coupon opertaions.

public class CouponService {

    // Function to validate coupon
    public boolean validate(Coupon coupon, double cartValue) {
        if (coupon == null) return false;

        if (coupon.isExpired()) return false;

        if (cartValue < coupon.getMinCartValue()) return false;

        return true;
    }

    // Validation with low scope (weak validation)
    public boolean validateCoupon(Coupon coupon) {
        if (coupon == null) return false;

        if (coupon.isExpired()) return false;

        return true;
    }

    // Function to calculate discount acc to coupon used.
    public double getDiscount(Coupon coupon, double cartValue) {

        if (!validate(coupon, cartValue)) return 0.0;

        switch (coupon.getType()) {
            case FLAT:
                return Math.min(coupon.getValue(), cartValue);
            case PERCENT:
                return (cartValue * coupon.getValue()) / 100.0;
            default:
                return 0.0;
        }
    }
}

