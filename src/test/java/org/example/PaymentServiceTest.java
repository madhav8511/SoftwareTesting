package org.example;

import org.example.payment.PaymentResult;
import org.example.payment.PaymentService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {

    // Dummy implementation for testing the abstract class
    static class DummyPaymentService extends PaymentService {
        @Override
        public PaymentResult processPayment(double amount) {
            if (!validateAmount(amount)) {
                return new PaymentResult(false, "Invalid amount", amount);
            }
            return new PaymentResult(true, "Processed", amount);
        }
    }

    @Test
    void testValidateAmount_PositiveValue() {
        PaymentService service = new DummyPaymentService();
        assertTrue(service.validateAmount(100.0));
    }

    @Test
    void testValidateAmount_NegativeValue() {
        PaymentService service = new DummyPaymentService();
        assertFalse(service.validateAmount(-5.0));
    }

    @Test
    void testValidateAmount_ZeroValue() {
        PaymentService service = new DummyPaymentService();
        assertFalse(service.validateAmount(0.0));
    }

    @Test
    void testValidateAmount_BoundarySmallPositive() {
        PaymentService service = new DummyPaymentService();
        assertTrue(service.validateAmount(0.0001));
    }

    @Test
    void testProcessPayment_ValidAmount() {
        PaymentService service = new DummyPaymentService();
        PaymentResult result = service.processPayment(50.0);

        assertTrue(result.getStatus());
        assertEquals("Processed", result.getMessage());
        assertEquals(50.0, result.getAmount());
    }

    @Test
    void testProcessPayment_InvalidAmount() {
        PaymentService service = new DummyPaymentService();
        PaymentResult result = service.processPayment(-10.0);

        assertFalse(result.getStatus());
        assertEquals("Invalid amount", result.getMessage());
        assertEquals(-10.0, result.getAmount());
    }

    @Test
    void testProcessPayment_ZeroReturnsFailure() {
        PaymentService service = new DummyPaymentService();
        PaymentResult result = service.processPayment(0.0);

        assertFalse(result.getStatus());
        assertEquals("Invalid amount", result.getMessage());
    }
}