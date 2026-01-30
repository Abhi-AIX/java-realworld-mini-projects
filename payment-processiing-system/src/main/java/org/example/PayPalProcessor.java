package org.example;

public class PayPalProcessor extends AbstractPaymentProcessor implements Refundable {

    private String email;

    public PayPalProcessor(String email) {
        this.email = email;
    }

    @Override
    protected void executePayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount + " for account: " + email);
    }

    @Override
    public void processRefund(double amount) {
        System.out.println("Processing PayPal refund of $" + amount + " for account: " + email);
    }
}
