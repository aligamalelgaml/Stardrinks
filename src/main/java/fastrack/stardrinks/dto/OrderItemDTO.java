package fastrack.stardrinks.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Builder
@Data
public class OrderItemDTO {
    @NotNull(message = "Product ID cannot be null")
    private UUID productId;

    @Min(value = 1, message = "Item quantity must be at least 1")
    private int itemQuantity;
}
