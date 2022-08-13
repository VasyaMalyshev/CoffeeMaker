package ru.malyshev.coffeemaker.service;

import ru.malyshev.coffeemaker.model.CoffeeDrink;

import java.util.List;

public interface CoffeeMakerService {

    List<CoffeeDrink> getAllCoffeeDrinks();
    CoffeeDrink makeCoffee(CoffeeDrink coffeeDrink);

}
