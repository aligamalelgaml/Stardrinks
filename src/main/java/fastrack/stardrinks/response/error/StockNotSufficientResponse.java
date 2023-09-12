package fastrack.stardrinks.response.error;

import fastrack.stardrinks.response.base.ErrorResponse;

public class StockNotSufficientResponse extends ErrorResponse {

    public StockNotSufficientResponse() {
    }

    public StockNotSufficientResponse(int status, String message, long timeStamp) {
        super(status, message, timeStamp);
    }
}
