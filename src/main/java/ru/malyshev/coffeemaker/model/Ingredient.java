package ru.malyshev.coffeemaker.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ingredients {

    @Id
    private Long Id;

    private String name;

    private Long count;
}
