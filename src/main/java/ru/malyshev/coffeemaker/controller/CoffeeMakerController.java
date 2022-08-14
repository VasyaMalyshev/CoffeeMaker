package ru.malyshev.coffeemaker.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.malyshev.coffeemaker.model.CoffeeDrink;
import ru.malyshev.coffeemaker.service.CoffeeMakerService;

import java.util.List;

@RestController
@RequestMapping("/drinks")
@RequiredArgsConstructor
public class CoffeeMakerController {
        private final CoffeeMakerService coffeeMakerService;

        @GetMapping
        public ResponseEntity<?> getAllCoffeeDrinks() {
                List<CoffeeDrink> allDrinks = coffeeMakerService.getAllCoffeeDrinks();
                return new ResponseEntity<>(allDrinks, HttpStatus.OK);
        }

        @PostMapping
        public ResponseEntity<?> addNewRecipe(@RequestBody CoffeeDrink coffeeDrink) {
                coffeeMakerService.addNewRecipe(coffeeDrink);
                return new ResponseEntity<>(HttpStatus.OK);
        }

        @GetMapping(value = "/{id}")
        public ResponseEntity<?> getOneCoffee(@PathVariable long id) {
                CoffeeDrink coffeeDrink = coffeeMakerService.getCoffeeById(id);
                return new ResponseEntity<>(coffeeDrink, HttpStatus.OK);
        }

        @PostMapping("/make")
        @ApiModelProperty(notes = "Coffee Drink Name", example = "Americano", required = true)
        public ResponseEntity<?> makeChosenCoffeeDrink(@RequestBody CoffeeDrink coffeeDrink) {
                coffeeMakerService.makeCoffee(coffeeDrink);
                return new ResponseEntity<>("Кофе готов", HttpStatus.OK);
        }
}