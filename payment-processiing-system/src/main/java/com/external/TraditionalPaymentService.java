package com.external;

public class TraditionalPaymentService {


    public void makePayment(TraditionalPayment payment, double amount) {
        if (payment.paymentType.equals("CREDIT_CARD") || payment.paymentType.equals("DEBIT_CARD")) {
            if (payment.balance >= amount) {
                payment.balance -= amount;
                payment.isSuccessful = true;
                System.out.println("Payment of $" + amount + " successful using " + payment.paymentType);
            } else if (payment.paymentType.equals("PAYPAL")) {
                payment.isSuccessful = true;
                System.out.println("Payment of $" + amount + " successful using " + payment.paymentType);
            } else if (payment.paymentType.equals("UPI")) {
                payment.isSuccessful = false;
                System.out.println("Payment of $" + amount + " failed using " + payment.paymentType);
            }

        }
    }
}
