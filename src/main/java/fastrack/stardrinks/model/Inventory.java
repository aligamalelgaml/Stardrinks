package fastrack.stardrinks.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Inventory {

    @Id
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

    @Column(name = "stock")
    int stock;

    public Inventory() {
    }

    public Inventory(UUID id, String name, int stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int quantity) {
        this.stock = quantity;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "name='" + name + '\'' +
                '}';
    }
}
