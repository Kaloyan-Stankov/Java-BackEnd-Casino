package homework_task.repositories;

import homework_task.entities.WinCoefficient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WinCoefficientRepository extends JpaRepository<WinCoefficient, Integer> {
    WinCoefficient findByWinningNumber(Integer winningNumber);
}
