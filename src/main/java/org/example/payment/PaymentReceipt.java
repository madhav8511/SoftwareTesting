package org.example.payment;

import java.time.LocalDateTime;

public class PaymentReceipt {
    private final String receiptId;
    private final LocalDateTime timestamp;
    private final PaymentMethod method;
    private final double originalAmount;
    private final double taxAmount;
    private final double finalAmount;

    public PaymentReceipt(String receiptId,
                          LocalDateTime timestamp,
                          PaymentMethod method,
                          double originalAmount,
                          double taxAmount,
                          double finalAmount) {
        this.receiptId = receiptId;
        this.timestamp = timestamp;
        this.method = method;
        this.originalAmount = originalAmount;
        this.taxAmount = taxAmount;
        this.finalAmount = finalAmount;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    @Override
    public String toString() {
        return "----------------------\n" +
                "       RECEIPT\n" +
                "----------------------\n" +
                "Receipt ID: " + receiptId + "\n" +
                "Timestamp : " + timestamp + "\n" +
                "Method    : " + method + "\n" +
                "Amount    : " + originalAmount + "\n" +
                "GST (18%) : " + taxAmount + "\n" +
                "Final Amt : " + finalAmount + "\n" +
                "----------------------";
    }
}