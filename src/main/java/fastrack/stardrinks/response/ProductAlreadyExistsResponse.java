package fastrack.stardrinks.response;

import fastrack.stardrinks.response.base.ErrorResponse;

public class ProductAlreadyExistsResponse extends ErrorResponse {

    public ProductAlreadyExistsResponse() {
    }

    public ProductAlreadyExistsResponse(int status, String message, long timeStamp) {
        super(status, message, timeStamp);
    }
}
