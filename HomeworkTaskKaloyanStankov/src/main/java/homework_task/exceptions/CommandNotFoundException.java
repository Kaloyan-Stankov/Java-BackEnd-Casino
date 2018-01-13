package homework_task.exceptions;

public class CommandNotFoundException extends Exception {
    private String message;

    public CommandNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
