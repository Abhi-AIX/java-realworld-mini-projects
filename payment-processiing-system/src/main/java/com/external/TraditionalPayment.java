package com.external;

public class TraditionalPayment {

    public String cardNumber;
    public  double balance;
    public String paymentType;
    public boolean isSuccessful;

    public TraditionalPayment(String cardNumber, double balance, String paymentType) {
        this.cardNumber = cardNumber;
        this.balance = balance;
        this.paymentType = paymentType;
        this.isSuccessful = false;
    }

}
