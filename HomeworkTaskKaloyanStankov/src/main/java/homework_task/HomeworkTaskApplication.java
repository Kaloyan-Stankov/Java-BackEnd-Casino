package homework_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"homework_task"})
public class HomeworkTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeworkTaskApplication.class, args);
    }
}
