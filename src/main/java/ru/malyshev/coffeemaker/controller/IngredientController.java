package ru.malyshev.coffeemaker.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.malyshev.coffeemaker.model.Ingredient;
import ru.malyshev.coffeemaker.service.IngredientService;

import java.util.List;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping()
    public ResponseEntity<?> getAllIngredients() {
        List<Ingredient> allIngredient = ingredientService.getAllIngredients();
        return new ResponseEntity<>(allIngredient, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> refillIngredients() {
        ingredientService.refillAllIngredients();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
