package ru.malyshev.coffeemaker.exception_handling;

public class CoffeeMakerException extends RuntimeException{
    public CoffeeMakerException(String message) {
        super(message);
    }
}
