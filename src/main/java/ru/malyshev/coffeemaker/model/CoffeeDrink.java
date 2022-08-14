package ru.malyshev.coffeemaker.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Getter
@Setter
@Table(name = "coffeeDrink")
@RequiredArgsConstructor
@NoArgsConstructor
public class CoffeeDrink {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Min(0)
    private Long water;
    @Min(0)
    private Long coffeeBeans;
    @Min(0)
    private Long milk;
    @Min(0)
    private Long sugar;

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
