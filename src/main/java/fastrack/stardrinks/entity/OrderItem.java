package fastrack.stardrinks.entity;

import jakarta.persistence.*;

@Embeddable
public class OrderItem {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Inventory product;

    @Column(name = "quantity")
    private int orderQuantity;

    public OrderItem() {
    }

    public OrderItem(Inventory product, int quantity) {
        this.product = product;
        this.orderQuantity = quantity;
    }

    public Inventory getProduct() {
        return product;
    }

    public void setProduct(Inventory product) {
        this.product = product;
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
                "product=" + product +
                ", quantity=" + orderQuantity +
                '}';
    }
}
