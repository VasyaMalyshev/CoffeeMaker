package ru.malyshev.coffeemaker.model;

import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@Table(name = "coffeeDrink")
public class CoffeeDrink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Min(0)
    @Max(1500)
    private Long water;

    private Long coffeeBeans;

    private Long milk;

    private Long sugar;

    public CoffeeDrink() {
    }

    public CoffeeDrink(Long id, String name, Long water, Long coffeeBeans, Long milk, Long sugar) {
        this.id = id;
        this.name = name;
        this.water = water;
        this.coffeeBeans = coffeeBeans;
        this.milk = milk;
        this.sugar = sugar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getWater() {
        return water;
    }

    public void setWater(Long water) {
        this.water = water;
    }

    public Long getCoffeeBeans() {
        return coffeeBeans;
    }

    public void setCoffeeBeans(Long coffeeBeans) {
        this.coffeeBeans = coffeeBeans;
    }

    public Long getMilk() {
        return milk;
    }

    public void setMilk(Long milk) {
        this.milk = milk;
    }

    public Long getSugar() {
        return sugar;
    }

    public void setSugar(Long sugar) {
        this.sugar = sugar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CoffeeDrink that = (CoffeeDrink) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (water != null ? !water.equals(that.water) : that.water != null) return false;
        if (coffeeBeans != null ? !coffeeBeans.equals(that.coffeeBeans) : that.coffeeBeans != null) return false;
        if (milk != null ? !milk.equals(that.milk) : that.milk != null) return false;
        return sugar != null ? sugar.equals(that.sugar) : that.sugar == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (water != null ? water.hashCode() : 0);
        result = 31 * result + (coffeeBeans != null ? coffeeBeans.hashCode() : 0);
        result = 31 * result + (milk != null ? milk.hashCode() : 0);
        result = 31 * result + (sugar != null ? sugar.hashCode() : 0);
        return result;
    }
}
