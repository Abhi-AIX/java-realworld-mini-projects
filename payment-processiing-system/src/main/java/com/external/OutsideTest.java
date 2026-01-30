package com.external;

import org.example.CreditCardProcessor;

public class OutsideTest {
    public static void main(String[] args) {

        TraditionalPayment traditionalPayment = new TraditionalPayment("1234567890123456", 1000.0, "CREDIT_CARD");
        TraditionalPaymentService traditionalPaymentService = new TraditionalPaymentService();
        traditionalPaymentService.makePayment(traditionalPayment, 200.0);

    }
}
