package org.example.payment;

import java.time.LocalDateTime;

// Class for printing receipt after payment
public class PaymentReceipt {

    //class fields
    // random receipt id
    private final String receiptId;
    // Local Data Time for timestamp
    private final LocalDateTime timestamp;
    // calls the enum for payment method
    private final PaymentMethod method;
    // amount before tax
    private final double originalAmount;
    // computed gst on the bill
    private final double taxAmount;
    // discount generated using coupon
    private final double discountAmount;
    // final amount to be paid
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