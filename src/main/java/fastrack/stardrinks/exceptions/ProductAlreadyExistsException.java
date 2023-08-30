package fastrack.stardrinks.exceptions;

import java.util.UUID;

public class ProductAlreadyExistsException extends RuntimeException {

    UUID foundId;

    public ProductAlreadyExistsException(String message, UUID foundId) {
        super(message);
        this.foundId = foundId;
    }

    public UUID getFoundId() {
        return foundId;
    }
}
