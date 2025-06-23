package easy.config;

import easy.service.GreetingService;
import easy.service.impl.EnglishGreetingService;
import easy.service.impl.KazakhGreetingService;
import easy.service.impl.RussianGreetingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@ComponentScan(basePackages = "easy")
public class AppConfig {

    @Bean(name = "englishGreetingService")
    @Primary
    public GreetingService englishGreetingService() {
        return new EnglishGreetingService();
    }

    @Bean(name = "kazakhGreetingService")
    public GreetingService kazakhGreetingService() {
        return new KazakhGreetingService();
    }

    @Bean(name = "russianGreetingService")
    public GreetingService russianGreetingService() {
        return new RussianGreetingService();
    }
}
