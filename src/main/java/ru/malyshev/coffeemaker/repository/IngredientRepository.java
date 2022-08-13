package ru.malyshev.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.malyshev.coffeemaker.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);
}