package ru.malyshev.coffeemaker.scheduler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.malyshev.coffeemaker.model.Ingredient;
import ru.malyshev.coffeemaker.service.IngredientService;

import java.util.List;

@Component
@RequiredArgsConstructor
public class IngredientScheduler {
    private final IngredientService ingredientService;

    @Scheduled(fixedDelay = 100000L)
    public void getAllIngredients() {
        List<Ingredient> allIngredients = ingredientService.getAllIngredients();
        System.out.println("Ингредиенты:");
        for (Ingredient ingredient : allIngredients) {
            System.out.println(ingredient.getName() + " : " + ingredient.getCount());
        }
    }
}
