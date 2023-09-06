package fastrack.stardrinks.model;

import fastrack.stardrinks.model.base.Product;
import jakarta.persistence.*;


@Entity
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    int id;

    @OneToOne
    Product product;

    @Column(name = "stock")
    int stock;

    public Inventory() {
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int quantity) {
        this.stock = quantity;
    }

    public Inventory(Product product, int stock) {
        this.product = product;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
