package ru.malyshev.coffeemaker.service;

import ru.malyshev.coffeemaker.model.Ingredient;

import java.util.List;

public interface IngredientService {

    List<Ingredient> getAllIngredients();
    void refillAllIngredients();
}
