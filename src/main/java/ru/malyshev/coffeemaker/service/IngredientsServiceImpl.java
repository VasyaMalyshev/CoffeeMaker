package ru.malyshev.coffeemaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
    public void refillIngredient(Ingredient ing) {
        System.out.println(ing.getName());
        Ingredient ingredient = ingredientRepository.findByName(ing.getName());
        if (ingredient.getName().equals("Water")) {
            ingredient.setCount(1000L);
        }
        if (ingredient.getName().equals("CoffeeBeans")) {
            ingredient.setCount(900L);
        }
        if (ingredient.getName().equals("Milk")) {
            ingredient.setCount(800L);
        }
        if (ingredient.getName().equals("Sugar")) {
            ingredient.setCount(700L);
        }
        ingredientRepository.save(ingredient);
    }

    @Override
    public void refillAllIngredients() {
        List<Ingredient> allIngredients = ingredientRepository.findAll();
        for (Ingredient ingredient : allIngredients) {
            if (ingredient.getName().equals("Water")) {
                ingredient.setCount(1000L);
                ingredientRepository.save(ingredient);
            }
            if (ingredient.getName().equals("CoffeeBeans")) {
                ingredient.setCount(900L);
                ingredientRepository.save(ingredient);
            }
            if (ingredient.getName().equals("Milk")) {
                ingredient.setCount(800L);
                ingredientRepository.save(ingredient);
            }
            if (ingredient.getName().equals("Sugar")) {
                ingredient.setCount(700L);
                ingredientRepository.save(ingredient);
            }
        }
    }
}
