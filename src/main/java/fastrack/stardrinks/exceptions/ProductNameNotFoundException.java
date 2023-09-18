package fastrack.stardrinks.exceptions;

public class ProductNameNotFoundException extends RuntimeException {
    String name;

    public ProductNameNotFoundException(String message, String name) {
        super(message);
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
