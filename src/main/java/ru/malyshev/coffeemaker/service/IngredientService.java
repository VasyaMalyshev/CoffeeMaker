package ru.malyshev.coffeemaker.service;

import ru.malyshev.coffeemaker.model.CoffeeDrink;
import ru.malyshev.coffeemaker.model.Ingredient;

import java.util.List;

public interface IngredientService {

    boolean checkIngredients(CoffeeDrink coffeeDrink);

}
