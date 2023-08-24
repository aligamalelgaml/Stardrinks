package fastrack.stardrinks.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    UUID id;

    @Column(name = "name")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "quantity")
    int quantity;

    public Inventory() {
    }

    public Inventory(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "name='" + name + '\'' +
                '}';
    }
}
