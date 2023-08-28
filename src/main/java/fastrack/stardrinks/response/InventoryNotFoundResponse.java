package fastrack.stardrinks.response;

import fastrack.stardrinks.response.base.ErrorResponse;

public class InventoryNotFoundResponse extends ErrorResponse {
    public InventoryNotFoundResponse() {
    }

    public InventoryNotFoundResponse(int status, String message, long timeStamp) {
        super(status, message, timeStamp);
    }
}
