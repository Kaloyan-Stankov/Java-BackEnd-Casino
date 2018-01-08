package homework_task.responses;

import org.springframework.http.HttpStatus;

public abstract class AbstractResponse {
    private HttpStatus status;
    private String uniqueId;

    public HttpStatus getStatus() {
        return status;
    }

    void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
