package fastrack.stardrinks.response.error;

import fastrack.stardrinks.response.base.ErrorResponse;

public class OrderMismatchResponse extends ErrorResponse {

    public OrderMismatchResponse() {
    }

    public OrderMismatchResponse(int status, String message, long timeStamp) {
        super(status, message, timeStamp);
    }
}
