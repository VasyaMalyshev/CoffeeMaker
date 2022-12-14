package ru.malyshev.coffeemaker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.malyshev.coffeemaker.model.CoffeeDrink;

@Repository
public interface CoffeeDrinkRepository extends JpaRepository<CoffeeDrink, Long> {
    CoffeeDrink findCoffeeDrinkByName(String name);
}