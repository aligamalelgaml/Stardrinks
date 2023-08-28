package fastrack.stardrinks.exceptions;

public class OrderNotFoundException extends RuntimeException {

    private final int orderID;

    public OrderNotFoundException(String message, int orderID) {
        super(message);
        this.orderID = orderID;
    }

    public int getOrderID() {
        return this.orderID;
    }

}
