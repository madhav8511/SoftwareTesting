package org.example.payment;

public class UPIPaymentService extends PaymentService {

    private String upiId;

    public UPIPaymentService(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public PaymentResult processPayment(double amount) {
        if (!validateAmount(amount)) {
            return new PaymentResult(false,
                    "Invalid amount", amount);
        }

        if (upiId == null || !upiId.contains("@")) {
            return new PaymentResult(false,
                    "Invalid UPI ID", amount);
        }

        return new PaymentResult(true,
                "UPI payment successful", amount);
    }
}