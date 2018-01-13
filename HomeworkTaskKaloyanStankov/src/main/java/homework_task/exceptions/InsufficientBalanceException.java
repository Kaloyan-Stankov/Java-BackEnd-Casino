package homework_task.exceptions;

public class InsufficientBalanceException extends Exception {
    private String message;

    public InsufficientBalanceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
