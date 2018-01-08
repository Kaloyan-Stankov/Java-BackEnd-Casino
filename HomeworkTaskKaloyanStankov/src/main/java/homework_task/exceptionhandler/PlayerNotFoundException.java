package homework_task.exceptionhandler;

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
