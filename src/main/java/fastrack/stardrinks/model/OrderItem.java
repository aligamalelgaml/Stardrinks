package fastrack.stardrinks.model;

import fastrack.stardrinks.dto.OrderItemDTO;
import fastrack.stardrinks.model.base.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Embeddable
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @ManyToOne
    private Product product;

    @Column(name = "quantity")
    private int itemQuantity;

    public OrderItemDTO mapToDto() {
        return OrderItemDTO.builder()
                .productId(this.product.getId())
                .itemQuantity(this.getItemQuantity())
                .build();
    }
}
