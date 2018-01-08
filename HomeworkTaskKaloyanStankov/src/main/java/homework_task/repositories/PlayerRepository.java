package homework_task.repositories;

import homework_task.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Integer> {
    Player findByUniqueId(String uniqueId);
}
