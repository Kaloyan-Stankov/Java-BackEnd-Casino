package homework_task;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"homework_task"})
//@EnableJpaRepositories("homework_task.repositories")
public class HomeworkTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(HomeworkTaskApplication.class, args);
    }
}
