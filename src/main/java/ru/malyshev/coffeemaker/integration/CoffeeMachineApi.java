package ru.malyshev.coffeemaker.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.malyshev.coffeemaker.model.CoffeeDrink;
import ru.malyshev.coffeemaker.model.Ingredient;
import ru.malyshev.coffeemaker.repository.IngredientRepository;


@Component
@RequiredArgsConstructor
public class CoffeeMachineApi implements CoffeeMachine {

    private final IngredientRepository ingredientRepository;

    public void makeCoffee(CoffeeDrink coffeeDrink) {

        System.out.println("Начинаю готовку кофе...");
        try {
            getCoffeeBeans(coffeeDrink);
            getWater(coffeeDrink);
            getMilk(coffeeDrink);
            getSugar(coffeeDrink);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Ваш кофе готов");
    }
    public void getCoffeeBeans(CoffeeDrink coffeeDrink) throws InterruptedException {
        System.out.println("Добавляю кофейные зерна");
        Thread.sleep(2000L);
        Ingredient coffeeBeans = ingredientRepository.findByName("CoffeeBeans");
        coffeeBeans.setCount(coffeeBeans.getCount() - coffeeDrink.getCoffeeBeans());
        ingredientRepository.save(coffeeBeans);
    }
    public void getWater(CoffeeDrink coffeeDrink) throws InterruptedException {
        System.out.println("Добавляю воду");
        Thread.sleep(2000L);
        Ingredient water = ingredientRepository.findByName("Water");
        water.setCount(water.getCount() - coffeeDrink.getWater());
        ingredientRepository.save(water);
    }

    public void getMilk(CoffeeDrink coffeeDrink) throws InterruptedException {
        Ingredient milk = ingredientRepository.findByName("Milk");
        if (coffeeDrink.getMilk() == 0) {
            ingredientRepository.save(milk);
        } else {
            System.out.println("Добавляю молоко");
            Thread.sleep(2000L);
            milk.setCount(milk.getCount() - coffeeDrink.getMilk());
            ingredientRepository.save(milk);
        }
    }
    public void getSugar(CoffeeDrink coffeeDrink) throws InterruptedException {
        Ingredient sugar = ingredientRepository.findByName("Sugar");
        if (coffeeDrink.getSugar() == 0) {
            ingredientRepository.save(sugar);
        } else {
            System.out.println("Добавляю сахар");
            Thread.sleep(2000L);
            sugar.setCount(sugar.getCount() - coffeeDrink.getSugar());
            ingredientRepository.save(sugar);
        }
    }
}
