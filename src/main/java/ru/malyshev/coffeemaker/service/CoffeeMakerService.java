package ru.malyshev.coffeemaker.service;

import ru.malyshev.coffeemaker.model.CoffeeDrink;

import java.util.List;

public interface CoffeeMakerService {

    List<CoffeeDrink> getAllCoffeeDrinks();
    CoffeeDrink getCoffeeById(Long id);
    void makeCoffee(CoffeeDrink coffeeDrink);

}
