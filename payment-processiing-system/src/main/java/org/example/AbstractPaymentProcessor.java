package org.example;

public abstract class AbstractPaymentProcessor implements PaymentProcessor {

    @Override
    public void processPayment(double amount) {
        validate(amount);
        log(amount);
        executePayment(amount);
    }
    /*
     why protected abstract method so that only subclasses can implement it and it
     cannot be accessed from outside the package and it enforces subclasses to provide
     their own implementation abstract method for specific payment execution each subclass
     will have its own way of executing payment
     */
    protected abstract void executePayment(double amount);

    protected void validate(double amount){
        if(amount <= 0){
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
    }

    protected void log(double amount){
        System.out.println("Processing payment of $" + amount);
    }
}
