package org.example.payment;

// Inherit class for card payment type
public class CardPaymentService extends PaymentService {

    // class fields
    private String cardNumber;

    //constructor
    public CardPaymentService(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    // method logic for card payment type
    @Override
    public PaymentResult processPayment(double amount) {
        // payment validation
        if (!validateAmount(amount)) {
            // return PaymentResult object of failure
            return new PaymentResult(false,
                    "Invalid amount", amount);
        }

        // null card number check
        // min length of card number check
        if (cardNumber == null || cardNumber.length() < 12) {
            // return PaymentResult object of failure
            return new PaymentResult(false,
                    "Invalid card number", amount);
        }

        // return PaymentResult object of success
        return new PaymentResult(true,
                "Card payment successful", amount);
    }
}