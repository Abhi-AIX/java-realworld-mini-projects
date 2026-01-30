package org.example;

public class DebitCardProcesser extends AbstractPaymentProcessor implements Refundable {

    private String cardNumber;

    public DebitCardProcesser(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    protected void executePayment(double amount) {
        System.out.println("Processing Debit Card payment of $" + amount + " for card: " + cardNumber);
    }

    @Override
    public void processRefund(double amount) {
        System.out.println("Processing Debit Card refund of $" + amount + " for card: " + cardNumber);
    }
}
