package homework_task.services;

import homework_task.entities.Bet;
import homework_task.entities.Player;
import homework_task.exceptionhandler.GameTypeNotFoundException;
import homework_task.exceptionhandler.InsufficientBalanceException;
import homework_task.games.DiceGame;
import homework_task.interfaces.Playable;
import homework_task.models.BetDTO;
import homework_task.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BetService {
    @Autowired
    private PlayerRepository playerRepository;

    public Bet placeBet(BetDTO betDTO) throws GameTypeNotFoundException, InsufficientBalanceException {
        Player player = playerRepository.findByUniqueId(betDTO.getUniqueId());
        if (player.getBalance().compareTo(betDTO.getAmount()) <= 0){
            throw new InsufficientBalanceException("Not enough funds to make this bet.");
        }
        //Placing a bet
        player.setBalance(player.getBalance().subtract(betDTO.getAmount()));

        //Easy to add new game types in the future
        switch (betDTO.getGameType()){
            case "dice":
                Playable game = new DiceGame();
                Integer gameResult = game.playGame();
                return null;
                default:
                    throw new GameTypeNotFoundException("Game type not found");
        }
    }
}
