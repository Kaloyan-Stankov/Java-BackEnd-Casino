package homework_task.services;

import homework_task.entities.Player;
import homework_task.exceptionhandler.PlayerExistException;
import homework_task.models.AuthenticationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import homework_task.repositories.PlayerRepository;

@Service
public class SignUpService {

    @Autowired
    private PlayerRepository playerRepository;
    public Player signUp(AuthenticationDTO authenticationDTO) throws PlayerExistException {
        Player player = new Player(authenticationDTO.getUniqueId(),authenticationDTO.getPlayerName(),authenticationDTO.getPassword());
       try {
           // Using same uniqueId updates the player

           return playerRepository.saveAndFlush(player);
       }catch (DataIntegrityViolationException ex){
           throw new PlayerExistException("Player name already exists");
       }

    }
}
