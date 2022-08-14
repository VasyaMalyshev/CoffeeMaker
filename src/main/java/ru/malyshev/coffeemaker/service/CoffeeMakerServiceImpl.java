package ru.malyshev.coffeemaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.malyshev.coffeemaker.exception_handling.CoffeeMakerException;
import ru.malyshev.coffeemaker.integration.CoffeeMachine;
import ru.malyshev.coffeemaker.model.CoffeeDrink;
import ru.malyshev.coffeemaker.repository.CoffeeDrinkRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeMakerServiceImpl implements CoffeeMakerService {

    private final CoffeeDrinkRepository coffeeDrinkRepository;
    private final CoffeeMachine coffeeMachine;
    private final IngredientService ingredientService;

    @Override
    public List<CoffeeDrink> getAllCoffeeDrinks() {
        return coffeeDrinkRepository.findAll();
    }

    @Override
    public void addNewRecipe(CoffeeDrink coffeeDrink) {
        coffeeDrinkRepository.save(coffeeDrink);
    }

    @Override
    public CoffeeDrink getCoffeeById(Long id) {
        return coffeeDrinkRepository.findById(id).get();
    }

    @Override
    public void makeCoffee(CoffeeDrink coffeeDrink) {

        CoffeeDrink drink = coffeeDrinkRepository.findCoffeeDrinkByName(coffeeDrink.getName());

        if (drink == null) {
            throw new CoffeeMakerException("Указанный кофе не найден в списке");
        }
        if (!ingredientService.checkIngredients(coffeeDrink)) {
            throw new CoffeeMakerException("Недостаточно ингредиентов");
        }
        coffeeMachine.makeCoffee(drink);
    }
}