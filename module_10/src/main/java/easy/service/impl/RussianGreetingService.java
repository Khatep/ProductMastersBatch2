package easy.service.impl;

import easy.service.GreetingService;

public class RussianGreetingService implements GreetingService {
    @Override
    public void sayHello() {
        System.out.println("Привет!");
    }
}
