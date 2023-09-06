package fastrack.stardrinks.model;

import fastrack.stardrinks.model.base.Product;
import fastrack.stardrinks.model.base.ResourceType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "coffee_beans")
public class CoffeeBean extends Product {
    public CoffeeBean(String name, LocalDate startMonth, LocalDate endMonth, BigDecimal price) {
        super(name, startMonth, endMonth, price);
    }

    @Override
    public ResourceType getType() {
        return ResourceType.BEANS;
    }

    public CoffeeBean() {
    }

    @Override
    public String toString() {
        return "Bean: " + super.toString();
    }
}
