package org.example;

public class CreditCardProcessor extends AbstractPaymentProcessor implements Refundable {

    private String cardNumber;

    public CreditCardProcessor(String cardNumber) {
        this.cardNumber = cardNumber;
    }

   @Override
    protected void executePayment(double amount) {
       String maskedCard = "****" + cardNumber.substring(cardNumber.length() - 4);
       System.out.println("Charging " + maskedCard + " for $" + amount);   }

    @Override
    public void processRefund(double amount) {
        System.out.println("Processing card refund of $" + amount);
    }
}
