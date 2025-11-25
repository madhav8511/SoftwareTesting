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
        // payment validation
        if (!validateAmount(amount)) {
            // return PaymentResult object of failure
            return new PaymentResult(false,
                    "Invalid amount", amount);
        }

        // null upi id check
        // chceking if valid upi id
        if (upiId == null || !upiId.contains("@")) {
            // return PaymentResult object of failure
            return new PaymentResult(false,
                    "Invalid UPI ID", amount);
        }

        // return PaymentResult object of success
        return new PaymentResult(true,
                "UPI payment successful", amount);
    }
}