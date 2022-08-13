package ru.malyshev.coffeemaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.coffeemaker.exception_handling.CoffeeMaker;
import ru.malyshev.coffeemaker.exception_handling.CoffeeMakerException;
import ru.malyshev.coffeemaker.model.CoffeeDrink;
import ru.malyshev.coffeemaker.model.Ingredient;
import ru.malyshev.coffeemaker.repository.CoffeeDrinkRepository;
import ru.malyshev.coffeemaker.repository.IngredientRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeMakerServiceImpl implements CoffeeMakerService {

    private final CoffeeDrinkRepository coffeeDrinkRepository;
    private final IngredientRepository ingredientRepository;

    @Override
    public List<CoffeeDrink> getAllCoffeeDrinks() {
        return coffeeDrinkRepository.findAll();
    }

    @Override
    public CoffeeDrink makeCoffee(CoffeeDrink coffeeDrink) {

        CoffeeDrink coffeeDrink1 = coffeeDrinkRepository.findCoffeeDrinkByName(coffeeDrink.getName());

        Ingredient water = ingredientRepository.findByName("Water");
        Ingredient milk = ingredientRepository.findByName("Milk");
        Ingredient coffeeBeans = ingredientRepository.findByName("CoffeeBeans");
        Ingredient sugar = ingredientRepository.findByName("Sugar");

        if (coffeeDrink1 != null) {
            water.setCount(checkAmountIngredients(water.getCount(), coffeeDrink1.getWater()));
            milk.setCount(checkAmountIngredients(milk.getCount(), coffeeDrink1.getMilk()));
            coffeeBeans.setCount(checkAmountIngredients(coffeeBeans.getCount(), coffeeDrink1.getCoffeeBeans()));
            sugar.setCount(checkAmountIngredients(sugar.getCount(), coffeeDrink1.getSugar()));

            ingredientRepository.save(water);
            ingredientRepository.save(milk);
            ingredientRepository.save(coffeeBeans);
            ingredientRepository.save(sugar);
        } else {
            throw new CoffeeMakerException("Указанный кофе не найден");
        }
        return coffeeDrink1;
    }

    public Long checkAmountIngredients(Long ing, Long coffee) {
        long count = ing - coffee;
        if (count  >= 0) {
            return count;
        } else {
            throw new CoffeeMakerException("Недостаточно ингредиентов для создания вашего кофе");
        }
    }
}
