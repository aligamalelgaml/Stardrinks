package fastrack.stardrinks.model;

import jakarta.persistence.*;

import java.util.UUID;

@Embeddable
public class OrderItem {

    private UUID productId;

    @Column(name = "quantity")
    private int orderQuantity;

    public OrderItem() {
    }

    public OrderItem(UUID productId, int quantity) {
        this.productId = productId;
        this.orderQuantity = quantity;
    }

    public UUID getProductId() {
        return productId;
    }

    public void setProductId(UUID productId) {
        this.productId = productId;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "productId=" + productId +
                ", quantity=" + orderQuantity +
                '}';
    }
}
