package ru.malyshev.coffeemaker.integration;

import ru.malyshev.coffeemaker.model.CoffeeDrink;

public interface CoffeeMachine {
    void makeCoffee(CoffeeDrink drink);
    Long getCurrentWater();
    Long getCurrentCoffeeBeans();
    Long getCurrentMilk();
    Long getCurrentSugar();
}
