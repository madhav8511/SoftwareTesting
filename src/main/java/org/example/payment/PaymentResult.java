package org.example.payment;

import java.util.*;
// Class for payment status
public class PaymentResult {

    //class fields
    // true or false denoting payment status
    private final boolean status;
    // string message
    private final String message;
    // final amount to be paid
    private final double amount;

    //constructor
    public PaymentResult(boolean status, String message, double amount) {
        this.status = status;
        this.message = message;
        this.amount = amount;
    }

    //Getter and Setter
    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public double getAmount() {
        return amount;
    }

    //Method to print payment status
    @Override
    public String toString() {

        return "PaymentResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", amount=" + amount +
                '}';
    }
}