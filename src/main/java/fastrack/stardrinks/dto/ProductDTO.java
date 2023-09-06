package fastrack.stardrinks.dto;

import fastrack.stardrinks.model.base.Product;
import fastrack.stardrinks.model.base.ResourceType;

import java.math.BigDecimal;
import java.time.LocalDate;

@Deprecated
public class ProductDTO extends Product {

    ResourceType type;

    public ProductDTO() {
    }

    public ProductDTO(String name, LocalDate startMonth, LocalDate endMonth, BigDecimal price, ResourceType type) {
        super(name, startMonth, endMonth, price);
        this.type = type;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }
}
