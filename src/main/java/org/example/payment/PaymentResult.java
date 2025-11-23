package org.example.payment;

import java.util.*;

public class PaymentResult {
    private final boolean status;
    private final String message;
    private final double amount;

    public PaymentResult(boolean status, String message, double amount) {
        this.status = status;
        this.message = message;
        this.amount = amount;
    }

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "PaymentResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", amount=" + amount +
                '}';
    }
}