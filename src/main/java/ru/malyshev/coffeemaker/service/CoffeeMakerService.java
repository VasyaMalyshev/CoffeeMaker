package ru.malyshev.coffeemaker.service;

import ru.malyshev.coffeemaker.model.CoffeeDrink;

import java.util.List;

public interface CoffeeMakerService {

    List<CoffeeDrink> getAllCoffeeDrinks();
    void addNewRecipe(CoffeeDrink coffeeDrink);
    CoffeeDrink getCoffeeById(Long id);
    void makeCoffee(CoffeeDrink coffeeDrink);

}
