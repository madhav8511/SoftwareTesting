package org.example;

import org.example.model.Coupon;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class CouponTest {

    @Test
    void constructor_ShouldCreateCoupon_WhenValidInput() {
        Coupon coupon = new Coupon("C100", Coupon.Type.FLAT, 100, 500,
                LocalDate.of(2030, 1, 1));

        assertEquals("C100", coupon.getCode());
        assertEquals(Coupon.Type.FLAT, coupon.getType());
        assertEquals(100, coupon.getValue());
        assertEquals(500, coupon.getMinCartValue());
        assertEquals(LocalDate.of(2030, 1, 1), coupon.getExpiry());
    }

    @Test
    void constructor_ShouldThrow_WhenCodeNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new Coupon(null, Coupon.Type.FLAT, 50, 200, LocalDate.now()));
    }

    @Test
    void constructor_ShouldThrow_WhenCodeEmpty() {
        assertThrows(IllegalArgumentException.class,
                () -> new Coupon("", Coupon.Type.FLAT, 50, 200, LocalDate.now()));
    }

    @Test
    void constructor_ShouldThrow_WhenTypeNull() {
        assertThrows(NullPointerException.class,
                () -> new Coupon("C1", null, 10, 100, LocalDate.now()));
    }

    @Test
    void testSetters() {
        Coupon coupon = new Coupon("C200", Coupon.Type.PERCENT, 10, 300,
                LocalDate.of(2030, 1, 1));

        coupon.setValue(20);
        coupon.setMinCartValue(500);
        coupon.setType(Coupon.Type.FLAT);
        coupon.setExpiry(LocalDate.of(2035, 12, 31));

        assertEquals(20, coupon.getValue());
        assertEquals(500, coupon.getMinCartValue());
        assertEquals(Coupon.Type.FLAT, coupon.getType());
        assertEquals(LocalDate.of(2035, 12, 31), coupon.getExpiry());
    }

    @Test
    void setCode_ShouldThrow_WhenNull() {
        Coupon coupon = new Coupon("C10", Coupon.Type.FLAT, 10, 100, LocalDate.now());
        assertThrows(IllegalArgumentException.class, () -> coupon.setCode(null));
    }

    @Test
    void setCode_ShouldThrow_WhenEmpty() {
        Coupon coupon = new Coupon("C10", Coupon.Type.FLAT, 10, 100, LocalDate.now());
        assertThrows(IllegalArgumentException.class, () -> coupon.setCode(""));
    }

    @Test
    void isExpired_ShouldReturnTrue_WhenExpiryBeforeToday() {
        Coupon coupon = new Coupon("C1", Coupon.Type.FLAT, 10, 100,
                LocalDate.now().minusDays(1));

        assertTrue(coupon.isExpired());
    }

    @Test
    void isExpired_ShouldReturnFalse_WhenExpiryAfterToday() {
        Coupon coupon = new Coupon("C1", Coupon.Type.FLAT, 10, 100,
                LocalDate.now().plusDays(1));

        assertFalse(coupon.isExpired());
    }

    @Test
    void isExpired_ShouldReturnFalse_WhenExpiryNull() {
        Coupon coupon = new Coupon("C1", Coupon.Type.FLAT, 10, 100, null);
        assertFalse(coupon.isExpired());
    }

    @Test
    void testToString_NotNull() {
        Coupon coupon = new Coupon("C1", Coupon.Type.PERCENT, 10, 100,
                LocalDate.of(2030, 1, 1));

        String str = coupon.toString();
        assertNotNull(str);
        assertTrue(str.contains("C1"));
        assertTrue(str.contains("PERCENT"));
    }
}
