package homework_task.exceptionhandler;

public class PlayerExistException extends Exception{
   private String message;

    public PlayerExistException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
