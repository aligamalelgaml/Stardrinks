package fastrack.stardrinks.exceptions;

public class StockNotSufficientException extends RuntimeException {

    private final int availableStock;

    public StockNotSufficientException(String message, int availableStock) {
        super(message);
        this.availableStock = availableStock;
    }

    public int getAvailableStock() {
        return availableStock;
    }
}
