package fastrack.stardrinks.model;

import fastrack.stardrinks.dto.OrderDTO;
import fastrack.stardrinks.dto.OrderItemDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    private User user;

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

    public OrderDTO mapToDto() {
        List<OrderItemDTO> orderItemDTOs = this.getOrderItems()
                .stream()
                .map(OrderItem::mapToDto) // Call mapToDto on each OrderItem
                .toList();

        return OrderDTO.builder()
                .id(this.getId())
                .userId(this.getUser().getId())
                .orderItems(orderItemDTOs)
                .build();
    }

}
