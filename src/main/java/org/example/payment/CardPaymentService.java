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
        if (!validateAmount(amount)) {
            return new PaymentResult(false,
                    "Invalid amount", amount);
        }

        if (cardNumber == null || cardNumber.length() < 12) {
            return new PaymentResult(false,
                    "Invalid card number", amount);
        }

        return new PaymentResult(true,
                "Card payment successful", amount);
    }
}