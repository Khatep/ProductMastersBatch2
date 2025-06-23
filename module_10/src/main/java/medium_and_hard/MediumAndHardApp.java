package medium_and_hard;

import medium_and_hard.config.DependencyInjectorConfig;
import medium_and_hard.service.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class MediumAndHardApp {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(DependencyInjectorConfig.class);

        OrderService orderService = context.getBean(OrderService.class);
        orderService.makeOrder(BigDecimal.valueOf(999));

        OrderService orderServiceSecond = context.getBean(OrderService.class);
        System.out.println("Один и тот же бин? : " + (orderService == orderServiceSecond));
    }
}
