package fastrack.stardrinks.response;

import fastrack.stardrinks.response.base.ErrorResponse;

public class ValidationFailedResponse extends ErrorResponse {

    public ValidationFailedResponse() {
    }

    public ValidationFailedResponse(int status, String message, long timeStamp) {
        super(status, message, timeStamp);
    }
}
