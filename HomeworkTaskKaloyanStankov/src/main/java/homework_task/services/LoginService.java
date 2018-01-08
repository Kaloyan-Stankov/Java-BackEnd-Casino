package homework_task.services;

import homework_task.entities.Player;
import homework_task.exceptionhandler.PlayerNotFoundException;
import homework_task.models.AuthenticationDTO;
import homework_task.models.Constants;
import homework_task.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player login(AuthenticationDTO authenticationDTO) throws PlayerNotFoundException {
        final Player player = playerRepository.findByUniqueId(authenticationDTO.getUniqueId());
        if (player != null) {
            if (player.getName().equals(authenticationDTO.getPlayerName())
                    && player.getPassword().equals(authenticationDTO.getPassword())) {
                return player;
            }
            throw new PlayerNotFoundException(Constants.INVALID_AUTHENTICATION);
        }
        throw new PlayerNotFoundException(Constants.PLAYER_NOT_FOUND);
    }
}
