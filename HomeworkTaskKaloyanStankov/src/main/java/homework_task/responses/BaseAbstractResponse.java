package homework_task.responses;

import org.springframework.http.HttpStatus;

public class BaseAbstractResponse extends AbstractResponse {
    public BaseAbstractResponse(String uniqueId, HttpStatus status) {
        setUniqueId(uniqueId);
        setStatus(status);
    }
}
