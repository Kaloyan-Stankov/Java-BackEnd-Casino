package homework_task.services;

import homework_task.entities.Bet;
import homework_task.entities.Player;
import homework_task.exceptionhandler.GameTypeNotFoundException;
import homework_task.exceptionhandler.InsufficientBalanceException;
import homework_task.games.DiceGame;
import homework_task.interfaces.Playable;
import homework_task.models.BetDTO;
import homework_task.models.BetPair;
import homework_task.models.Constants;
import homework_task.repositories.BetRepository;
import homework_task.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class BetService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private BetRepository betRepository;

    public Bet placeBet(BetDTO betDTO) throws GameTypeNotFoundException, InsufficientBalanceException {
        Player player = playerRepository.findByUniqueId(betDTO.getUniqueId());
        if (player.getBalance().compareTo(betDTO.getAmount()) <= 0) {
            throw new InsufficientBalanceException(Constants.INSUFFICIENT_BALANCE_BET);
        }
        //Placing a bet
        player.setBalance(player.getBalance().subtract(betDTO.getAmount()));

        //Easy to add new game types in the future
        switch (betDTO.getGameType()) {
            case "dice":
                Playable game = new DiceGame();
                Integer gameResult = game.playGame();
                final Optional<BetPair> winningBet = betDTO.getAdditionalData().stream().filter(betPair -> betPair.getU().equals(gameResult)).findAny();
                Bet bet = null;
                if (winningBet.isPresent()) {
                    //TODO Calculate coefficient
                    bet = new Bet(player, betDTO.getAmount(), true, BigDecimal.valueOf(winningBet.get().getV() * 2.0), gameResult);
                    player.setBalance(player.getBalance().add(bet.getWinAmount()));
                } else {
                    bet = new Bet(player, betDTO.getAmount(), false, BigDecimal.ZERO, gameResult);
                }
                betRepository.saveAndFlush(bet);
                playerRepository.saveAndFlush(player);

                return bet;
            default:
                throw new GameTypeNotFoundException(Constants.GAME_TYPE_NOT_FOUND);
        }
    }
}
