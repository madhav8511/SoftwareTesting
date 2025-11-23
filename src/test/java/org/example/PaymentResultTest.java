package org.example;

import org.example.payment.PaymentResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentResultTest {
    @Test
    void testConstructorAndGetters_SuccessCase() {
        PaymentResult result = new PaymentResult(true, "Payment successful", 500.0);

        assertTrue(result.getStatus());
        assertEquals("Payment successful", result.getMessage());
        assertEquals(500.0, result.getAmount());
    }

    @Test
    void testConstructorAndGetters_FailureCase() {
        PaymentResult result = new PaymentResult(false, "Insufficient balance", 0.0);

        assertFalse(result.getStatus());
        assertEquals("Insufficient balance", result.getMessage());
        assertEquals(0.0, result.getAmount());
    }

    @Test
    void testZeroAmount() {
        PaymentResult result = new PaymentResult(true, "Free order", 0.0);

        assertEquals(0.0, result.getAmount());
        assertEquals("Free order", result.getMessage());
    }

    @Test
    void testNegativeAmount() {
        PaymentResult result = new PaymentResult(false, "Refund applied", -100.0);

        assertEquals(-100.0, result.getAmount());
        assertEquals("Refund applied", result.getMessage());
    }

    @Test
    void testEmptyMessage() {
        PaymentResult result = new PaymentResult(true, "", 250.0);

        assertEquals("", result.getMessage());
        assertEquals(250.0, result.getAmount());
        assertTrue(result.getStatus());
    }

    @Test
    void testToStringContainsAllFields() {
        PaymentResult result = new PaymentResult(true, "OK", 120.5);
        String output = result.toString();

        assertTrue(output.contains("status=true"));
        assertTrue(output.contains("message='OK'"));
        assertTrue(output.contains("amount=120.5"));
    }

    @Test
    void testToStringNotNull() {
        PaymentResult result = new PaymentResult(false, "Fail", 300.0);
        assertNotNull(result.toString());
    }

    @Test
    void testImmutability_Status() {
        PaymentResult result = new PaymentResult(true, "Message", 10.0);
        assertTrue(result.getStatus());
    }

    @Test
    void testImmutability_Message() {
        PaymentResult result = new PaymentResult(true, "Immutable", 10.0);
        assertEquals("Immutable", result.getMessage());
    }

    @Test
    void testImmutability_Amount() {
        PaymentResult result = new PaymentResult(false, "Test", 999.99);
        assertEquals(999.99, result.getAmount());
    }
}
