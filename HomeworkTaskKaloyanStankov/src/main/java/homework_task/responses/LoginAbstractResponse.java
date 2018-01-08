package homework_task.responses;

import org.springframework.http.HttpStatus;

public class LoginAbstractResponse extends AbstractResponse {
    private String token;

    public LoginAbstractResponse(String uniqueId, HttpStatus status, String token) {
        setUniqueId(uniqueId);
        setStatus(status);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
