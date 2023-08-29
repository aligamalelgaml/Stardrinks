package fastrack.stardrinks.exceptions;

public class OrderMismatchException extends RuntimeException {

    private int uriId;

    private int bodyId;


    public OrderMismatchException(String message, int uriId, int bodyId) {
        super(message);
        this.uriId = uriId;
        this.bodyId = bodyId;
    }

    public int getUriId() {
        return uriId;
    }

    public int getBodyId() {
        return bodyId;
    }
}
