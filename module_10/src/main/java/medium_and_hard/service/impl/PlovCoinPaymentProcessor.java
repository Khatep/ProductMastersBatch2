package medium_and_hard.service.impl;

import medium_and_hard.service.PaymentProcessor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PlovCoinPaymentProcessor implements PaymentProcessor {
    @Override
    public void processPayment(BigDecimal amount) {
        System.out.println("Оплачиваю через PlovCoin : " + amount);
    }
}
