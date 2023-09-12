package fastrack.stardrinks.response.error;

import fastrack.stardrinks.response.base.ErrorResponse;

public class OrderNotFoundResponse extends ErrorResponse {

    public OrderNotFoundResponse() {
    }

    public OrderNotFoundResponse(int status, String message, long timeStamp) {
        super(status, message, timeStamp);
    }
}
