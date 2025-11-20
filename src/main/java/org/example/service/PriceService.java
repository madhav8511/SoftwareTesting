package org.example.service;

import org.example.model.CartItem;
import org.example.model.Coupon;

import java.util.Collection;

public class PriceService {

    private final CouponService couponService;
    private final GSTService gstService;

    public PriceService(CouponService couponService, GSTService gstService) {
        this.couponService = couponService;
        this.gstService = gstService;
    }

    public double calculateFinalTotal(Collection<CartItem> items, Coupon coupon) {

        double subtotal = items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();

        double discount = couponService.getDiscount(coupon, subtotal);

        double gst = gstService.calculateTotalGST(items);

        return subtotal - discount + gst;
    }
}
