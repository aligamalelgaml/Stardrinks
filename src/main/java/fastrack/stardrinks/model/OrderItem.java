package fastrack.stardrinks.model;

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

    @Override
    public String toString() {
        return "OrderItem{" +
                "product=" + product +
                ", itemQuantity=" + itemQuantity +
                '}';
    }
}
