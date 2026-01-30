package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        PaymentProcessor processor1 = new CreditCardProcessor("123456781");
        processor1.processPayment(100);
        if(processor1 instanceof Refundable){
            Refundable refundable = (Refundable) processor1;
            refundable.processRefund(20);
        }

        PaymentProcessor processor2 = new PayPalProcessor("akfmav");
        processor2.processPayment(250);
        if(processor2 instanceof Refundable){
            Refundable refundable = (Refundable) processor2;
            refundable.processRefund(75);
        }

        PaymentProcessor processor3 = new DebitCardProcesser("1A1zP1eP5QGefi2DMPTfTL5SLmv7DivfNa");
        processor3.processPayment(300);
        if(processor3 instanceof Refundable){
            Refundable refundable = (Refundable) processor3;
            refundable.processRefund(100);
        }

/*
//This volites the DIP as we are depending on concrete classes
        //rather than abstractions
        //Better to depend on interfaces or abstract classes
        //Also we are duplicating code for processRefund method in both classes
        CreditCardProcessor processor = new CreditCardProcessor("123456781");
        processor.processPayment(100);

        if(processor instanceof Refundable){
            Refundable refundable = (Refundable) processor;
            refundable.processRefund(20);
        }

        PayPalProcessor paypal = new PayPalProcessor("akfmav");
        paypal.processPayment(250);

        if(paypal instanceof Refundable){
            Refundable refundable = (Refundable) paypal;
            refundable.processRefund(75);
        }




 */
    }
}