package org.example;

import org.example.model.Coupon;
import org.example.service.CouponService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CouponServiceTest {
    @Test
    void testNullCoupon_NoDiscount() {
        CouponService service = new CouponService();
        double discount = service.getDiscount(null, 1000);
        assertEquals(0, discount);
    }

    @Test
    void testFlatDiscount() {
        CouponService service = new CouponService();
        Coupon c = new Coupon("FLAT50", Coupon.Type.FLAT, 50, 0, LocalDate.now().plusDays(5));
        double discount = service.getDiscount(c, 500);
        assertEquals(50, discount);
    }

    @Test
    void testPercentageDiscount() {
        CouponService service = new CouponService();
        Coupon c = new Coupon("P10", Coupon.Type.PERCENT, 10, 0, LocalDate.now().plusDays(5));
        double discount = service.getDiscount(c, 1000);
        assertEquals(100, discount); // 10% of 1000
    }

    @Test
    void testDiscountCannotExceedSubtotal() {
        CouponService service = new CouponService();
        Coupon c = new Coupon("FLAT200", Coupon.Type.FLAT, 200, 0, LocalDate.now().plusDays(5));
        double discount = service.getDiscount(c, 150);
        assertEquals(150, discount); // discount capped to subtotal
    }

    @Test
    void testMinCartValue_NotApplied() {
        CouponService service = new CouponService();
        Coupon c = new Coupon("FLAT50", Coupon.Type.FLAT, 50, 500, LocalDate.now().plusDays(5));
        double discount = service.getDiscount(c, 300);
        assertEquals(0, discount); // subtotal < minCartValue
    }

    @Test
    void testCouponExpired_NotApplied() {
        CouponService service = new CouponService();
        Coupon c = new Coupon("FLAT50", Coupon.Type.FLAT, 50, 0, LocalDate.now().minusDays(1));
        double discount = service.getDiscount(c, 1000);
        assertEquals(0, discount); // coupon expired
    }

    @Test
    void testMinCartValueAndExpiry_Applied() {
        CouponService service = new CouponService();
        Coupon c = new Coupon("P10", Coupon.Type.PERCENT, 10, 500, LocalDate.now().plusDays(1));
        double discount = service.getDiscount(c, 600);
        assertEquals(60, discount); // 10% of 600
    }
}

