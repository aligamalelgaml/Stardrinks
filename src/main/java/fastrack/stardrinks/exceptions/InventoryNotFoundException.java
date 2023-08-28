package fastrack.stardrinks.exceptions;

import java.util.UUID;

public class InventoryNotFoundException extends RuntimeException {

    private final UUID id;

    public InventoryNotFoundException(String message, UUID id) {
        super(message);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
