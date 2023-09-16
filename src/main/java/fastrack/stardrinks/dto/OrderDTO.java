package fastrack.stardrinks.dto;

import fastrack.stardrinks.model.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderDTO {

    // Optional id, for when updating orders.
    private Integer id;

    @NotNull
    private Integer userId;

    @Size(min = 1, message = "Order must have at least one item")
    @Valid
    private List<OrderItemDTO> orderItems;
}
