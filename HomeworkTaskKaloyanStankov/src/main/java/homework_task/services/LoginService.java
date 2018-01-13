package homework_task.services;

import homework_task.entities.Player;
import homework_task.exceptions.PlayerNotFoundException;
import homework_task.generators.TokenGenerator;
import homework_task.dtos.AuthenticationDTO;
import homework_task.Constants;
import homework_task.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    private PlayerRepository playerRepository;

    public Player login(AuthenticationDTO authenticationDTO) throws PlayerNotFoundException {
        Player player = playerRepository.findByUniqueId(authenticationDTO.getUniqueId());
        if (player != null) {
            if (player.getName().equals(authenticationDTO.getPlayerName())
                    && player.getPassword().equals(authenticationDTO.getPassword())) {
                player.setToken(TokenGenerator.getToken());
                playerRepository.saveAndFlush(player);
                return player;
            }
            throw new PlayerNotFoundException(Constants.INVALID_AUTHENTICATION);
        }
        throw new PlayerNotFoundException(Constants.PLAYER_NOT_FOUND);
    }
}
