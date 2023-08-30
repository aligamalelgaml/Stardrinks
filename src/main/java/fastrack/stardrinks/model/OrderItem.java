package fastrack.stardrinks.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Embeddable
public class OrderItem {

    // TODO: Rewrite to use Product entity instead of UUID.
    @NotNull(message = "Product ID cannot be null")
    private UUID productId;

    @Column(name = "quantity")
    @Min(value = 1, message = "Item quantity must be at least 1")
    private int itemQuantity;

    public OrderItem() {
    }

    public OrderItem(UUID productId, int quantity) {
        this.productId = productId;
        this.itemQuantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
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
                "productId=" + productId +
                ", quantity=" + itemQuantity +
                '}';
    }
}
