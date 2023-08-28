package fastrack.stardrinks.response;

import fastrack.stardrinks.response.base.ErrorResponse;

public class OrderNotFoundResponse extends ErrorResponse {

    public OrderNotFoundResponse() {
    }

    public OrderNotFoundResponse(int status, String message, long timeStamp) {
        super(status, message, timeStamp);
    }
}
