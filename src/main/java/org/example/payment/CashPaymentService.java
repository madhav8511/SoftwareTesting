package org.example.payment;

// Inherit class for cash payment type
public class CashPaymentService extends PaymentService {

    // method logic for cash payment type
    @Override
    public PaymentResult processPayment(double amount) {
        if (!validateAmount(amount)) {
            return new PaymentResult(false,
                    "Invalid amount", amount);
        }

        return new PaymentResult(true,
                "Cash payment accepted", amount);
    }
}