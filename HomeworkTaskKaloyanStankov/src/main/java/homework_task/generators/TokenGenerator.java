package homework_task.generators;

import java.util.UUID;

public class TokenGenerator {
    public static String getToken() {
        return UUID.randomUUID().toString();
    }
}
