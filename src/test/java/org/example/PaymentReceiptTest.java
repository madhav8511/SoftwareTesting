package org.example;

import org.example.payment.PaymentMethod;
import org.example.payment.PaymentReceipt;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentReceiptTest {

    @Test
    void testConstructorAndGetters() {
        String receiptId = "ABC123";
        LocalDateTime timestamp = LocalDateTime.now();
        PaymentMethod method = PaymentMethod.CARD;
        double originalAmount = 100.0;
        double taxAmount = 18.0;
        double finalAmount = 118.0;

        PaymentReceipt receipt = new PaymentReceipt(
                receiptId,
                timestamp,
                method,
                originalAmount,
                taxAmount,
                finalAmount
        );

        assertEquals(receiptId, receipt.getReceiptId());
        assertEquals(timestamp, receipt.getTimestamp());
        assertEquals(method, receipt.getMethod());
        assertEquals(originalAmount, receipt.getOriginalAmount());
        assertEquals(taxAmount, receipt.getTaxAmount());
        assertEquals(finalAmount, receipt.getFinalAmount());
    }

    @Test
    void testToStringContainsAllFields() {
        String receiptId = "XYZ-789";
        LocalDateTime timestamp = LocalDateTime.of(2025, 1, 1, 10, 30);
        PaymentMethod method = PaymentMethod.UPI;
        double originalAmount = 500.0;
        double taxAmount = 90.0;
        double finalAmount = 590.0;

        PaymentReceipt receipt = new PaymentReceipt(
                receiptId,
                timestamp,
                method,
                originalAmount,
                taxAmount,
                finalAmount
        );

        String result = receipt.toString();

        assertTrue(result.contains("RECEIPT"));
        assertTrue(result.contains(receiptId));
        assertTrue(result.contains(timestamp.toString()));
        assertTrue(result.contains(method.toString()));
        assertTrue(result.contains(String.valueOf(originalAmount)));
        assertTrue(result.contains(String.valueOf(taxAmount)));
        assertTrue(result.contains(String.valueOf(finalAmount)));
    }

    @Test
    void testToStringFormatNotEmpty() {
        PaymentReceipt receipt = new PaymentReceipt(
                "R1",
                LocalDateTime.now(),
                PaymentMethod.CASH,
                100,
                18,
                118
        );

        String text = receipt.toString();

        assertNotNull(text);
        assertFalse(text.isBlank());
    }

    @Test
    void testImmutability() {
        LocalDateTime time = LocalDateTime.now();
        PaymentReceipt receipt = new PaymentReceipt(
                "ID1",
                time,
                PaymentMethod.CARD,
                10,
                1.8,
                11.8
        );

        // Ensures getters return same values every time (immutability)
        assertEquals("ID1", receipt.getReceiptId());
        assertEquals(time, receipt.getTimestamp());
        assertEquals(PaymentMethod.CARD, receipt.getMethod());
        assertEquals(10, receipt.getOriginalAmount());
        assertEquals(1.8, receipt.getTaxAmount());
        assertEquals(11.8, receipt.getFinalAmount());
    }
}
