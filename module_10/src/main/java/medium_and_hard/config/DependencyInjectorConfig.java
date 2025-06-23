package medium_and_hard.config;

import medium_and_hard.service.PaymentProcessor;
import medium_and_hard.service.impl.BitcoinPaymentProcessor;
import medium_and_hard.service.impl.MasterCardPaymentProcessor;
import medium_and_hard.service.impl.PlovCoinPaymentProcessor;
import medium_and_hard.service.impl.VisaCardPaymentProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "medium_and_hard")
public class DependencyInjectorConfig {

    @Bean
    @Primary
    public PaymentProcessor visa() {
        return new VisaCardPaymentProcessor();
    }

    @Bean(name = "masterCard")
    public PaymentProcessor masterCard() {
        return new MasterCardPaymentProcessor();
    }

    @Bean(name = "bitcoin")
    public PaymentProcessor bitcoin() {
        return new BitcoinPaymentProcessor();
    }

    @Bean(name = "plovCoin")
    public PaymentProcessor plovCoin() {
        return new PlovCoinPaymentProcessor();
    }
}