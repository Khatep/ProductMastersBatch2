package easy;

import easy.config.AppConfig;
import easy.service.GreetingService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Getter
@Component
public class EasyApp {

    private final GreetingService greetingService;

    @Autowired
    public EasyApp(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EasyApp easyApp = context.getBean(EasyApp.class);
        easyApp.getGreetingService().sayHello();
    }
}
