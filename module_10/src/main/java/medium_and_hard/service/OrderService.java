package medium_and_hard.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OrderService {

    private final PaymentProcessor paymentProcessor;

    @Autowired
    //visa, mastercard, bitcoin, plovCoin
    public OrderService(@Qualifier("bitcoin") PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
        System.out.println("OrderService создан с процессором: " + paymentProcessor.getClass().getSimpleName());
    }

    public void makeOrder(BigDecimal amount) {
        paymentProcessor.processPayment(amount);
    }
}
