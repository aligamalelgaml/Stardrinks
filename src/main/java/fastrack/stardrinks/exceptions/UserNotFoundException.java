package fastrack.stardrinks.exceptions;

public class UserNotFoundException extends RuntimeException {

    private final int userId;

    public UserNotFoundException(String message, int userId) {
        super(message);
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
