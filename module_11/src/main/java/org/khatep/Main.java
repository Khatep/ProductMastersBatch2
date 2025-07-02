package org.khatep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }
}

/*
Реализовать простой REST API для получения списка фильмов.

Требования: Создать модель Movie с полями:

String title

String director

int year

Создать интерфейс MovieRepository и его реализацию, которая возвращает список заранее созданных фильмов.

Создать сервис MovieService, который использует MovieRepository.

Создать контроллер MovieController, в котором будет GET-метод /api/movies/all.
* */