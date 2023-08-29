package fastrack.stardrinks.dto;

import fastrack.stardrinks.model.base.Product;
import fastrack.stardrinks.model.base.ResourceType;

import java.time.LocalDate;

public class ProductDTO extends Product {

    ResourceType type;

    public ProductDTO() {
    }

    public ProductDTO(String name, LocalDate startMonth, LocalDate endMonth, ResourceType type) {
        super(name, startMonth, endMonth);
        this.type = type;
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }
}
