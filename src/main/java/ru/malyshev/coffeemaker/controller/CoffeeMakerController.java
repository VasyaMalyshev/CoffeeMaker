package ru.malyshev.coffeemaker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.coffeemaker.exception_handling.CoffeeMakerException;
import ru.malyshev.coffeemaker.model.CoffeeDrink;
import ru.malyshev.coffeemaker.model.Ingredient;
import ru.malyshev.coffeemaker.service.CoffeeMakerService;
import ru.malyshev.coffeemaker.service.IngredientService;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class CoffeeMakerController {
        private final CoffeeMakerService coffeeMakerService;
        private final IngredientService ingredientService;

        @GetMapping(value = "allCoffeeDrinks")
        public ResponseEntity<?> getAllCoffeeDrinks() {
                List<CoffeeDrink> allDrinks = coffeeMakerService.getAllCoffeeDrinks();
                return new ResponseEntity<>(allDrinks, HttpStatus.OK);
        }

        @PostMapping(value = "makeChosenCoffee")
        public ResponseEntity<?> makeChosenCoffeeDrink(@RequestBody CoffeeDrink coffeeDrink) {
                System.out.println("Ваш " + coffeeDrink.getName() + " готовится. Ожидайте 10 секунд");
                CoffeeDrink coffeeDrink1 = coffeeMakerService.makeCoffee(coffeeDrink);
                try {
                        Thread.sleep(5000L);
                } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                }
                System.out.println("Ваш "+ coffeeDrink.getName() + " готов");
                return new ResponseEntity<>(coffeeDrink1, HttpStatus.OK);
        }


        @GetMapping(value = "AllIngredients")
        public ResponseEntity<?> getAllIngredients() {
                List<Ingredient> allIngredient = ingredientService.getAllIngredients();
                return new ResponseEntity<>(allIngredient, HttpStatus.OK);
        }

        @PostMapping(value = "refillOneIngredient")
        public ResponseEntity<?> refillIngredients(@RequestBody Ingredient ing) {
                if (ing.getCount() < 0) throw new CoffeeMakerException("Указано отрицательное значение");
                ingredientService.refillIngredient(ing);
                return new ResponseEntity<>(HttpStatus.OK);
        }

        @PostMapping(value = "refillAllIngredients")
        public ResponseEntity<?> refillIngredients() {
                ingredientService.refillAllIngredients();
                return new ResponseEntity<>(HttpStatus.OK);
        }


}
