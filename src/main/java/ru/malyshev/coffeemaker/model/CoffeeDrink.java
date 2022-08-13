package ru.malyshev.coffeemaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CoffeeMachine {

    @Id
    private Long id;

    private String name;

}
