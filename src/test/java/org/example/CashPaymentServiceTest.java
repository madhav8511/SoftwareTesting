package org.example;

import org.example.payment.CashPaymentService;
import org.example.payment.PaymentResult;
import org.example.payment.PaymentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CashPaymentServiceTest {

    @Test
    void testProcessPayment_InvalidAmount_Negative() {
        PaymentService service = new CashPaymentService();

        PaymentResult result = service.processPayment(-10);

        assertFalse(result.getStatus());
        assertEquals("Invalid amount", result.getMessage());
        assertEquals(-10, result.getAmount());
    }

    @Test
    void testProcessPayment_InvalidAmount_Zero() {
        PaymentService service = new CashPaymentService();

        PaymentResult result = service.processPayment(0);

        assertFalse(result.getStatus());
        assertEquals("Invalid amount", result.getMessage());
        assertEquals(0, result.getAmount());
    }

    @Test
    void testProcessPayment_ValidAmount() {
        PaymentService service = new CashPaymentService();

        PaymentResult result = service.processPayment(500);

        assertTrue(result.getStatus());
        assertEquals("Cash payment accepted", result.getMessage());
        assertEquals(500, result.getAmount());
    }

    @Test
    void testProcessPayment_BoundaryAmount_JustAboveZero() {
        PaymentService service = new CashPaymentService();

        PaymentResult result = service.processPayment(0.01);

        assertTrue(result.getStatus());
        assertEquals("Cash payment accepted", result.getMessage());
        assertEquals(0.01, result.getAmount());
    }
}