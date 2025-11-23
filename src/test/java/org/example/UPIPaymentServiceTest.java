package org.example;

import org.example.payment.PaymentResult;
import org.example.payment.PaymentService;
import org.example.payment.UPIPaymentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UPIPaymentServiceTest {

    @Test
    void testInvalidAmount_Negative() {
        PaymentService service = new UPIPaymentService("user@upi");

        PaymentResult result = service.processPayment(-100);

        assertFalse(result.getStatus());
        assertEquals("Invalid amount", result.getMessage());
        assertEquals(-100, result.getAmount());
    }

    @Test
    void testInvalidAmount_Zero() {
        PaymentService service = new UPIPaymentService("user@upi");

        PaymentResult result = service.processPayment(0);

        assertFalse(result.getStatus());
        assertEquals("Invalid amount", result.getMessage());
        assertEquals(0, result.getAmount());
    }

    @Test
    void testInvalidUpi_Null() {
        PaymentService service = new UPIPaymentService(null);

        PaymentResult result = service.processPayment(100);

        assertFalse(result.getStatus());
        assertEquals("Invalid UPI ID", result.getMessage());
        assertEquals(100, result.getAmount());
    }

    @Test
    void testInvalidUpi_NoAtSymbol() {
        PaymentService service = new UPIPaymentService("invalidupi");

        PaymentResult result = service.processPayment(200);

        assertFalse(result.getStatus());
        assertEquals("Invalid UPI ID", result.getMessage());
        assertEquals(200, result.getAmount());
    }

    @Test
    void testValidUpiPayment() {
        PaymentService service = new UPIPaymentService("user@axis");

        PaymentResult result = service.processPayment(500);

        assertTrue(result.getStatus());
        assertEquals("UPI payment successful", result.getMessage());
        assertEquals(500, result.getAmount());
    }

    @Test
    void testBoundary_JustAboveZero() {
        PaymentService service = new UPIPaymentService("abc@upi");

        PaymentResult result = service.processPayment(0.01);

        assertTrue(result.getStatus());
        assertEquals("UPI payment successful", result.getMessage());
        assertEquals(0.01, result.getAmount());
    }
}