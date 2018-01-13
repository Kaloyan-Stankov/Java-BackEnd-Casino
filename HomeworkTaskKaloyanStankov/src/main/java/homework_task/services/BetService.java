package homework_task.services;

import homework_task.entities.Bet;
import homework_task.entities.Player;
import homework_task.exceptions.GameTypeNotFoundException;
import homework_task.exceptions.InsufficientBalanceException;
import homework_task.games.DiceGame;
import homework_task.interfaces.Playable;
import homework_task.dtos.BetDTO;
import homework_task.dtos.BetPairDTO;
import homework_task.Constants;
import homework_task.repositories.BetRepository;
import homework_task.repositories.PlayerRepository;
import homework_task.repositories.WinCoefficientRepository;
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
    @Autowired
    private WinCoefficientRepository winCoefficientRepository;

    public Bet placeBet(BetDTO betDTO) throws GameTypeNotFoundException, InsufficientBalanceException {
        Player player = playerRepository.findByUniqueId(betDTO.getUniqueId());
        if (player.getBalance().compareTo(betDTO.getAmount()) <= 0) {
            throw new InsufficientBalanceException(Constants.INSUFFICIENT_BALANCE_BET);
        }
        //Placing a bet
        player.setBalance(player.getBalance().subtract(betDTO.getAmount()));

        //Using switch-case to easily add new game types in the future
        switch (betDTO.getGameType()) {
            case "dice":
                Playable game = new DiceGame();
                Integer gameResult = game.playGame();
                final Optional<BetPairDTO> winningBet = betDTO.getAdditionalData().stream().filter(betPairDTO -> betPairDTO.getU().equals(gameResult)).findAny();
                Bet bet;
                if (winningBet.isPresent()) {
                    bet = new Bet(player, betDTO.getAmount(), true,
                            BigDecimal.valueOf(winningBet.get().getV() * (1.00 + winCoefficientRepository.findByWinningNumber(winningBet.get().getU()).getWinningCoefficient())), gameResult);
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
