package fastrack.stardrinks.model;

import fastrack.stardrinks.model.base.Product;
import fastrack.stardrinks.model.base.ResourceType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "coffee_beans")
public class CoffeeBean extends Product {
    public CoffeeBean(String name, LocalDate startMonth, LocalDate endMonth) {
        super(name, startMonth, endMonth);
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
