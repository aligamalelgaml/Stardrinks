package fastrack.stardrinks.model;

import fastrack.stardrinks.model.base.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "drinks")
public class Drink extends Product {

    public Drink(String name, LocalDate startMonth, LocalDate endMonth) {
        super(name, startMonth, endMonth);
    }

    public Drink() {
    }

    @Override
    public String toString() {
        return "Drink: " + super.toString();
    }
}
