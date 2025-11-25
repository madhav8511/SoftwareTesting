package org.example.payment;

// Inherit class for cash payment type
public class CashPaymentService extends PaymentService {

    // method logic for cash payment type
    @Override
    public PaymentResult processPayment(double amount) {
        // payment validation
        if (!validateAmount(amount)) {
            // return PaymentResult object of failure
            return new PaymentResult(false,
                    "Invalid amount", amount);
        }

        // return PaymentResult object of success
        return new PaymentResult(true,
                "Cash payment accepted", amount);
    }
}