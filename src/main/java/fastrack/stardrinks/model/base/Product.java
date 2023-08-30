package fastrack.stardrinks.model.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fastrack.stardrinks.model.CoffeeBean;
import fastrack.stardrinks.model.Drink;
import fastrack.stardrinks.model.Goodie;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Drink.class, name = "DRINKS"),
        @JsonSubTypes.Type(value = CoffeeBean.class, name = "BEANS"),
        @JsonSubTypes.Type(value = Goodie.class, name = "GOODIES")
})
public abstract class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "start_month")
    private LocalDate startMonth;

    @Column(name = "end_month")
    private LocalDate endMonth;

    public Product() {
    }

    public Product(String name, LocalDate startMonth, LocalDate endMonth) {
        this.name = name;
        this.startMonth = startMonth;
        this.endMonth = endMonth;
    }

    @JsonProperty("type")
    public abstract ResourceType getType(); // Return the enum indicating the type

    public String getName() {
        return name;
    }

    public LocalDate getStartMonth() {
        return startMonth;
    }

    public LocalDate getEndMonth() {
        return endMonth;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartMonth(LocalDate startMonth) {
        this.startMonth = startMonth;
    }

    public void setEndMonth(LocalDate endMonth) {
        this.endMonth = endMonth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name) && startMonth == product.startMonth && endMonth == product.endMonth;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, startMonth, endMonth);
    }

    @Override
    public String toString() {
        return String.format("%s, valid from: %s to %s", this.name, this.startMonth.toString(), this.endMonth.toString());
    }
}
