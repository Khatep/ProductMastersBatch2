package easy.service.impl;

import easy.service.GreetingService;

public class KazakhGreetingService implements GreetingService {

    @Override
    public void sayHello() {
        System.out.println("Сәлем!");
    }
}
