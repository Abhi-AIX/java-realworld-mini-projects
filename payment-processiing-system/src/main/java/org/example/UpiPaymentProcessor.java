package org.example;

public class UpiPaymentProcessor extends AbstractPaymentProcessor implements Refundable {

    @Override
    public void executePayment(double amount) {
         System.out.println("Processing UPI payment of $" + amount);
    }

    @Override
    public void processRefund(double amount) {
        System.out.println("Processing UPI refund of $" + amount);
    }
}
