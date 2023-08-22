package fastrack.stardrinks.entity;

import fastrack.stardrinks.base.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "coffee_beans")
public class CoffeeBean extends Product {
    public CoffeeBean(String name, LocalDate startMonth, LocalDate endMonth) {
        super(name, startMonth, endMonth);
    }

    public CoffeeBean() {
    }

    @Override
    public String toString() {
        return "Bean: " + super.toString();
    }
}
