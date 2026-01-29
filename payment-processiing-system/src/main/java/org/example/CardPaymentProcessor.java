package org.example;

public class CardPaymentProcessor extends AbstractPaymentProcessor implements Refundable {

   @Override
    public void executePayment(double amount) {
         System.out.println("Processing card payment of $" + amount);
   }

    @Override
    public void processRefund(double amount) {
        System.out.println("Processing card refund of $" + amount);
    }
}
