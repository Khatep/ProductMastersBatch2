package easy.service.impl;

import easy.service.GreetingService;

public class EnglishGreetingService implements GreetingService {
    @Override
    public void sayHello() {
        System.out.println("Hello!");
    }
}
