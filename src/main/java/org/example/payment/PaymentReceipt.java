package org.example.payment;

import java.time.LocalDateTime;

// Class for printing receipt after payment
public class PaymentReceipt {

    //class fields
    private final String receiptId;
    private final LocalDateTime timestamp;
    private final PaymentMethod method;
    private final double originalAmount;
    private final double taxAmount;
    private final double discountAmount;
    private final double finalAmount;

    // constructor
    public PaymentReceipt(String receiptId,
                          LocalDateTime timestamp,
                          PaymentMethod method,
                          double originalAmount,
                          double taxAmount,
                          double discount,
                          double finalAmount) {
        this.receiptId = receiptId;
        this.timestamp = timestamp;
        this.method = method;
        this.originalAmount = originalAmount;
        this.taxAmount = taxAmount;
        this.discountAmount = discount;
        this.finalAmount = finalAmount;
    }

    // Getter and Setter
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

    //Overriding method to print receipt
    @Override
    public String toString() {
        return "----------------------\n" +
                "       RECEIPT\n" +
                "----------------------\n" +
                "Receipt ID: " + receiptId + "\n" +
                "Timestamp : " + timestamp + "\n" +
                "Method    : " + method + "\n" +
                "Amount    : " + originalAmount + "\n" +
                "Discount : " + discountAmount + "\n" +
                "GST : " + taxAmount + "\n" +
                "Final Amt : " + finalAmount + "\n" +
                "----------------------";
    }
}