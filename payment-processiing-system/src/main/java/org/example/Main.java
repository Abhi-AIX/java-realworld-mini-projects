package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


        PaymentProcessor card = new CardPaymentProcessor();
        PaymentProcessor upi = new UpiPaymentProcessor();

        CheckoutService checkoutServiceUpi = new CheckoutService(upi);
        checkoutServiceUpi.checkout(200.0);

        CheckoutService checkoutService = new CheckoutService(card);
        checkoutService.checkout(100.0);

        if(card instanceof Refundable){
            Refundable refundable = (Refundable) card;
            refundable.processRefund(50.0);
        }

    }
}