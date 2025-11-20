package org.example.service;

import org.example.model.Coupon;

public class CouponService {

    public boolean validate(Coupon coupon, double cartValue) {
        if (coupon == null) return false;

        if (coupon.isExpired()) return false;

        if (cartValue < coupon.getMinCartValue()) return false;

        return true;
    }

    public boolean validateCoupon(Coupon coupon) {
        if (coupon == null) return false;

        if (coupon.isExpired()) return false;

        return true;
    }

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

