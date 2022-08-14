package ru.malyshev.coffeemaker.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ru.malyshev.coffeemaker.exception_handling.CoffeeMakerException;
import ru.malyshev.coffeemaker.model.CoffeeDrink;
import ru.malyshev.coffeemaker.model.Ingredient;
import ru.malyshev.coffeemaker.service.IngredientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Component
public class CoffeeMachineMock implements CoffeeMachine {
    private final IngredientService ingredientService;
    private final Map<Ingredients, Long> currentCount = new HashMap<>();
    public enum Ingredients {
        WATER, MILK, COFFEE_BEANS, SUGAR
    }
    @Autowired
    public CoffeeMachineMock(@Lazy IngredientService ingredientService) {
        this.ingredientService = ingredientService;
        currentCount.put(Ingredients.WATER, 2000L);
        currentCount.put(Ingredients.COFFEE_BEANS, 500L);
        currentCount.put(Ingredients.MILK, 1000L);
        currentCount.put(Ingredients.SUGAR, 200L);
    }
    @Override
    public synchronized void makeCoffee(CoffeeDrink coffeeDrink) {
        log.info("Начинаю готовку кофе...");
        try {
            if (!ingredientService.checkIngredients(coffeeDrink)) {
                throw new CoffeeMakerException("Недостаточно ингредиентов");
            }
            putCoffeeBeans(coffeeDrink);
            putWater(coffeeDrink);
            putMilk(coffeeDrink);
            putSugar(coffeeDrink);
        } catch (InterruptedException e) {
            throw new RuntimeException("Ошибка при приготовлении кофе");
        }
        log.info("Ваш кофе готов");
    }
    @Override
    public Long getCurrentWater() {
        return currentCount.get(Ingredients.WATER);
    }
    @Override
    public Long getCurrentCoffeeBeans() {
        return currentCount.get(Ingredients.COFFEE_BEANS);
    }
    @Override
    public Long getCurrentMilk() {
        return currentCount.get(Ingredients.MILK);
    }
    @Override
    public Long getCurrentSugar() {
        return currentCount.get(Ingredients.SUGAR);
    }
    private void putCoffeeBeans(CoffeeDrink coffeeDrink) throws InterruptedException {
        log.info("Добавляю кофейные зерна");
        putIngredient(coffeeDrink.getIngredients(), Ingredients.COFFEE_BEANS);
        log.info("Зерна добавлены. Осталось " + currentCount.get(Ingredients.COFFEE_BEANS));
    }
    private void putWater(CoffeeDrink coffeeDrink) throws InterruptedException {
        log.info("Добавляю воду");
        putIngredient(coffeeDrink.getIngredients(), Ingredients.WATER);
        log.info("Вода налита. Осталось " + currentCount.get(Ingredients.WATER));
    }
    private void putMilk(CoffeeDrink coffeeDrink) throws InterruptedException {
        log.info("Добавляю молоко");
        putIngredient(coffeeDrink.getIngredients(), Ingredients.MILK);
        log.info("Молоко налито. Осталось " + currentCount.get(Ingredients.MILK));
    }
    private void putSugar(CoffeeDrink coffeeDrink) throws InterruptedException {
        log.info("Добавляю сахар");
        putIngredient(coffeeDrink.getIngredients(), Ingredients.SUGAR);
        log.info("Сахар добавлен. Осталось " + currentCount.get(Ingredients.SUGAR));
    }
    private void putIngredient(List<Ingredient> ingredientList, Ingredients ingredients) throws InterruptedException {
        Optional<Ingredient> ingredientChosen = ingredientList.stream()
                .filter(ingredient -> ingredient.getName().equals(ingredients))
                .findFirst();
        if (ingredientChosen.isEmpty()) return;
        Ingredient c = ingredientChosen.get();
        long count = currentCount.get(ingredients) - c.getCount();
        currentCount.put(ingredients, count);
        Thread.sleep(2000L);
    }
}