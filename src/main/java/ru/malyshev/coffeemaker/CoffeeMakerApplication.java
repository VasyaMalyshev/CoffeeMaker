package ru.malyshev.coffeemaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class CoffeeMakerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffeeMakerApplication.class, args);
    }

}
