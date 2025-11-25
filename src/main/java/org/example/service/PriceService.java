package org.example.service;

import org.example.model.CartItem;
import org.example.model.Coupon;

import java.util.Collection;

// Service to implement prices operations.
public class PriceService {


    // Required two service
    private final CouponService couponService;
    private final GSTService gstService;

    // Constructor
    public PriceService(CouponService couponService, GSTService gstService) {
        this.couponService = couponService;
        this.gstService = gstService;
    }

    // Function to calculate total cart value after applying discount and tax.
    public double calculateFinalTotal(Collection<CartItem> items, Coupon coupon) {
        // computing total
        double subtotal = items.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();

        // integration method calling
        double discount = couponService.getDiscount(coupon, subtotal);

        // integration method calling
        double gst = gstService.calculateTotalGST(items);

        // final price
        return subtotal - discount + gst;
    }
}
