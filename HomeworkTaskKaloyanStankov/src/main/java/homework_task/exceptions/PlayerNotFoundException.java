package homework_task.exceptions;

public class PlayerNotFoundException extends Exception {
    private String message;

    public PlayerNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
