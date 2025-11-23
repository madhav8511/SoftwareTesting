package org.example.payment;

public class CardPaymentService extends PaymentService {

    private String cardNumber;

    public CardPaymentService(String cardNumber) {
        this.cardNumber = cardNumber;
    }

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