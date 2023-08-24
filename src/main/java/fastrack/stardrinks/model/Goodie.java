package fastrack.stardrinks.model;

import fastrack.stardrinks.model.base.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "seasonal_goodies")
public class Goodie extends Product {
    public Goodie(String name, LocalDate startMonth, LocalDate endMonth) {
        super(name, startMonth, endMonth);
    }

    public Goodie() {
    }

    @Override
    public String toString() {
        return "Goodie: " + super.toString();
    }
}
