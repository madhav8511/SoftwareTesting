package org.example.payment;

// Parent class for different type of payments methods.
public abstract class PaymentService {

    /**
     * Processes a payment of a given amount.
     * Concrete classes must implement their own logic.
     */
    public abstract PaymentResult processPayment(double amount);

    /**
     * A simple utility method that all children can use.
     */
    public boolean validateAmount(double amount) {
        return amount > 0;
    }
}