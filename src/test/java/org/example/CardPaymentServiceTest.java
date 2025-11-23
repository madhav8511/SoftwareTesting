package org.example;

import org.example.payment.CardPaymentService;
import org.example.payment.PaymentResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardPaymentServiceTest {

    @Test
    void testProcessPayment_InvalidAmount_Negative() {
        CardPaymentService service = new CardPaymentService("123456789012");
        PaymentResult result = service.processPayment(-50);

        assertFalse(result.getStatus());
        assertEquals("Invalid amount", result.getMessage());
        assertEquals(-50, result.getAmount());
    }

    @Test
    void testProcessPayment_InvalidAmount_Zero() {
        CardPaymentService service = new CardPaymentService("123456789012");
        PaymentResult result = service.processPayment(0);

        assertFalse(result.getStatus());
        assertEquals("Invalid amount", result.getMessage());
    }

    @Test
    void testProcessPayment_InvalidCard_NullCard() {
        CardPaymentService service = new CardPaymentService(null);
        PaymentResult result = service.processPayment(100);

        assertFalse(result.getStatus());
        assertEquals("Invalid card number", result.getMessage());
        assertEquals(100, result.getAmount());
    }

    @Test
    void testProcessPayment_InvalidCard_ShortCard() {
        CardPaymentService service = new CardPaymentService("12345");
        PaymentResult result = service.processPayment(100);

        assertFalse(result.getStatus());
        assertEquals("Invalid card number", result.getMessage());
    }

    @Test
    void testProcessPayment_BoundaryCardLength() {
        CardPaymentService service = new CardPaymentService("123456789012"); // exactly 12
        PaymentResult result = service.processPayment(250);

        assertTrue(result.getStatus());
        assertEquals("Card payment successful", result.getMessage());
        assertEquals(250, result.getAmount());
    }

    @Test
    void testProcessPayment_ValidLongCard() {
        CardPaymentService service = new CardPaymentService("1234567890123456");
        PaymentResult result = service.processPayment(500);

        assertTrue(result.getStatus());
        assertEquals("Card payment successful", result.getMessage());
        assertEquals(500, result.getAmount());
    }
}