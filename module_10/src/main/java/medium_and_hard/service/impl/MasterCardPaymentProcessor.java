package medium_and_hard.service.impl;

import medium_and_hard.service.PaymentProcessor;

import java.math.BigDecimal;

public class MasterCardPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(BigDecimal amount) {
        System.out.println("Оплачиваю через MasterCard карту: " + amount);
    }
}
