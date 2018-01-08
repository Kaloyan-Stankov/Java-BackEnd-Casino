package homework_task.exceptionhandler;

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
