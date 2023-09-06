package fastrack.stardrinks.model;

import fastrack.stardrinks.model.base.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Embeddable
public class OrderItem {

    @NotNull(message = "Product cannot be null")
    @ManyToOne
    private Product product;

    @Column(name = "quantity")
    @Min(value = 1, message = "Item quantity must be at least 1")
    private int itemQuantity;

    public OrderItem() {
    }

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.itemQuantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
