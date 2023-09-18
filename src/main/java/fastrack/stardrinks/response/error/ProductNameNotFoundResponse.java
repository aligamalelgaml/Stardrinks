package fastrack.stardrinks.response.error;

import fastrack.stardrinks.response.base.ErrorResponse;

public class ProductNameNotFoundResponse extends ErrorResponse {

    public ProductNameNotFoundResponse() {
    }

    public ProductNameNotFoundResponse(int status, String message, long timeStamp) {
        super(status, message, timeStamp);
    }
}
