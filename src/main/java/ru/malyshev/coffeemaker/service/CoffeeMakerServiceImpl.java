package ru.malyshev.coffeemaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.coffeemaker.exception_handling.CoffeeMakerException;
import ru.malyshev.coffeemaker.integration.CoffeeMachine;
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
    private final CoffeeMachine coffeeMachine;

    @Override
    public List<CoffeeDrink> getAllCoffeeDrinks() {
        return coffeeDrinkRepository.findAll();
    }

    @Override
    public CoffeeDrink getCoffeeById(Long id) {
        return coffeeDrinkRepository.findById(id).get();
    }

    @Override
    public void makeCoffee(CoffeeDrink coffeeDrink) {

        CoffeeDrink drink = coffeeDrinkRepository.findCoffeeDrinkByName(coffeeDrink.getName());

        if (drink != null && checkAmountIngredients(coffeeDrink)) {
            coffeeMachine.makeCoffee(drink);
        } else {
            throw new CoffeeMakerException("Указанный кофе не найден в списке или недостаточное количество ингредиентов");
        }
    }

    private boolean checkAmountIngredients(CoffeeDrink coffeeDrink) {

        Ingredient water = ingredientRepository.findByName("Water");
        Ingredient milk = ingredientRepository.findByName("Milk");
        Ingredient coffeeBeans = ingredientRepository.findByName("CoffeeBeans");
        Ingredient sugar = ingredientRepository.findByName("Sugar");

        boolean waterBoolean = (water.getCount() - coffeeDrink.getWater()) > 0;
        boolean milkBoolean = (milk.getCount() - coffeeDrink.getMilk()) > 0;
        boolean coffeeBeansBoolean = (coffeeBeans.getCount() - coffeeDrink.getCoffeeBeans()) > 0;
        boolean sugarBoolean = (sugar.getCount() - coffeeDrink.getSugar() > 0);

        return waterBoolean && milkBoolean && coffeeBeansBoolean && sugarBoolean;
    }
}
