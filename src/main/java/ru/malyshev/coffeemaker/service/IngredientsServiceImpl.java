package ru.malyshev.coffeemaker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import ru.malyshev.coffeemaker.integration.CoffeeMachine;
import ru.malyshev.coffeemaker.model.CoffeeDrink;
import ru.malyshev.coffeemaker.model.Ingredient;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientsServiceImpl implements IngredientService {

    private final CoffeeMachine coffeeMachine;

    @Override
    public boolean checkIngredients(CoffeeDrink coffeeDrink) {
        List<Ingredient> ingredients = coffeeDrink.getIngredients();
        for (Ingredient ingredient : ingredients) {
            switch (ingredient.getName()) {
                case WATER:
                    if (coffeeMachine.getCurrentWater() - ingredient.getCount() < 0){
                        return false;
                    }
                    break;
                case COFFEE_BEANS:
                    if (coffeeMachine.getCurrentCoffeeBeans() - ingredient.getCount() < 0){
                        return false;
                    }
                    break;
                case MILK:
                    if (coffeeMachine.getCurrentMilk() - ingredient.getCount() < 0){
                        return false;
                    }
                    break;
                case SUGAR:
                    if (coffeeMachine.getCurrentSugar() - ingredient.getCount() < 0){
                        return false;
                    }
                    break;
            }

        }
        return true;
    }
}
