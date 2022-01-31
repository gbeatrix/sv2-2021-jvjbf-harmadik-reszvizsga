package vehiclerental;

public class UserNameIsAlreadyTakenException extends IllegalArgumentException {
    public UserNameIsAlreadyTakenException() {
    }

    public UserNameIsAlreadyTakenException(String message) {
        super(message);
    }

    public UserNameIsAlreadyTakenException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameIsAlreadyTakenException(Throwable cause) {
        super(cause);
    }
}
