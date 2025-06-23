package medium_and_hard.service;

import java.math.BigDecimal;

public interface PaymentProcessor {
    void processPayment(BigDecimal amount);
}
