package org.example;

public class CheckoutService {

    //why data should be private final PaymentProcessor paymentProcessor;
    //Making the paymentProcessor private ensures encapsulation, preventing external classes from directly accessing or modifying it.
    //Declaring it as final guarantees that the reference cannot be changed after initialization, promoting immutability and thread safety.
    //Together, these practices enhance the robustness and maintainability of the code.
    private final PaymentProcessor paymentProcessor;

    public CheckoutService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void checkout(double amount) {
        paymentProcessor.processPayment(amount);
    }
}
