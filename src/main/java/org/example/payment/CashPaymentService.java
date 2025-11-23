package org.example.payment;

public class CashPaymentService extends PaymentService {

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