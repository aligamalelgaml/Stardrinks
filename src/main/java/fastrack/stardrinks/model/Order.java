package fastrack.stardrinks.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    private List<OrderItem> orderItems;

    @PrePersist
    private void timestamp() {
        this.orderDate = LocalDateTime.now();
    }



    public Order() {
        this.orderItems = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        this.orderItems.add(item);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    @Override
    public String toString() {
        return "ShopOrder{" +
                "id=" + id +
                ", orderItems=" + orderItems +
                '}';
    }
}
