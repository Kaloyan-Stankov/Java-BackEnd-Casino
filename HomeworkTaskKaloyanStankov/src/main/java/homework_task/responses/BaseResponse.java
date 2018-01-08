package homework_task.responses;

import org.springframework.http.HttpStatus;

public class BaseResponse extends AbstractResponse {
    public BaseResponse(String uniqueId, HttpStatus status) {
        setUniqueId(uniqueId);
        setStatus(status);
    }
}
