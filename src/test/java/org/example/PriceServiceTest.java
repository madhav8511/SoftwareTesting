package org.example;

import org.example.model.CartItem;
import org.example.model.Coupon;
import org.example.model.Item;
import org.example.service.CouponService;
import org.example.service.GSTService;
import org.example.service.PriceService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceServiceTest {

    @Test
    void testFinalTotal_WithPercentageCoupon() {
        CouponService couponService = new CouponService();
        GSTService gstService = new GSTService();
        PriceService priceService = new PriceService(couponService, gstService);

        Item item = new Item("A1", "Apple", 200, 5);
        CartItem cartItem = new CartItem(item, 2);
        // subtotal = 400

        Coupon coupon = new Coupon(
                "P10",
                Coupon.Type.PERCENT,
                10,              // value = 10%
                0,               // minCartValue
                LocalDate.now().plusDays(10)
        );

        double total = priceService.calculateFinalTotal(List.of(cartItem), coupon);

        double expected = 400 - 40 + 20; // 10% discount & 5% GST
        assertEquals(expected, total);
    }

    @Test
    void testFinalTotal_WithFlatCoupon() {
        CouponService couponService = new CouponService();
        GSTService gstService = new GSTService();
        PriceService priceService = new PriceService(couponService, gstService);

        Item item = new Item("B1", "Bread", 50, 12);
        CartItem cartItem = new CartItem(item, 3);
        // subtotal = 150

        Coupon coupon = new Coupon(
                "FLAT30",
                Coupon.Type.FLAT,
                30,                // flat discount
                0,
                LocalDate.now().plusDays(10)
        );

        // GST = 12% of 150 = 18

        double total = priceService.calculateFinalTotal(List.of(cartItem), coupon);
        double expected = 150 - 30 + 18;

        assertEquals(expected, total);
    }

    @Test
    void testFinalTotal_NoCoupon() {
        CouponService couponService = new CouponService();
        GSTService gstService = new GSTService();
        PriceService priceService = new PriceService(couponService, gstService);

        Item item = new Item("C1", "Milk", 100, 5);
        CartItem cartItem = new CartItem(item, 1);

        double total = priceService.calculateFinalTotal(List.of(cartItem), null);

        assertEquals(105.0, total);
    }

    @Test
    void testFinalTotal_DiscountCannotExceedSubtotal() {
        CouponService couponService = new CouponService();
        GSTService gstService = new GSTService();
        PriceService priceService = new PriceService(couponService, gstService);

        Item item = new Item("D1", "Pen", 20, 10);
        CartItem cartItem = new CartItem(item, 1);

        Coupon coupon = new Coupon(
                "FLAT50",
                Coupon.Type.FLAT,
                50,     // discount > subtotal → capped by service
                0,
                LocalDate.now().plusDays(10)
        );

        double total = priceService.calculateFinalTotal(List.of(cartItem), coupon);
        double expected = 20 - 20 + 2;

        assertEquals(expected, total);
    }
}