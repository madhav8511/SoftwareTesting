package org.example.payment;

// Inherit class for upi payment type
public class UPIPaymentService extends PaymentService {

    //class field
    private String upiId;

    //constructor
    public UPIPaymentService(String upiId) {
        this.upiId = upiId;
    }

    // method logic for cash payment type
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