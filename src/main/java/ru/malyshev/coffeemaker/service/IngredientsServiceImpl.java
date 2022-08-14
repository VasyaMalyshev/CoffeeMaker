package ru.malyshev.coffeemaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.malyshev.coffeemaker.exception_handling.CoffeeMakerException;
import ru.malyshev.coffeemaker.model.Ingredient;
import ru.malyshev.coffeemaker.repository.IngredientRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientsServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;

    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public void refillAllIngredients() {
        List<Ingredient> allIngredients = ingredientRepository.findAll();
        for (Ingredient ingredient : allIngredients) {
            long LIQUID_MAX = 1000L;
            if (ingredient.getName().equals("Water")) {
                ingredient.setCount(LIQUID_MAX);
                ingredientRepository.save(ingredient);
            }
            if (ingredient.getName().equals("CoffeeBeans")) {
                long BEAN_MAX = 500L;
                ingredient.setCount(BEAN_MAX);
                ingredientRepository.save(ingredient);
            }
            if (ingredient.getName().equals("Milk")) {
                ingredient.setCount(LIQUID_MAX);
                ingredientRepository.save(ingredient);
            }
            if (ingredient.getName().equals("Sugar")) {
                long SUGAR_MAX = 300L;
                ingredient.setCount(SUGAR_MAX);
                ingredientRepository.save(ingredient);
            }
        }
        System.out.println("Ингредиенты пополнены");
    }
}
