package homework_task.exceptions;

public class GameTypeNotFoundException extends Exception {
    private String message;

    public GameTypeNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
