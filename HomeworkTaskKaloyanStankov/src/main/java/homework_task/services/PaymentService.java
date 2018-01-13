package homework_task.services;

import homework_task.entities.Payment;
import homework_task.entities.Player;
import homework_task.exceptions.CommandNotFoundException;
import homework_task.exceptions.InsufficientBalanceException;
import homework_task.exceptions.PlayerNotFoundException;
import homework_task.Constants;
import homework_task.dtos.PaymentDTO;
import homework_task.repositories.PaymentRepository;
import homework_task.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public Payment processPayment(PaymentDTO paymentDTO) throws InsufficientBalanceException, CommandNotFoundException, PlayerNotFoundException {
        Player player = playerRepository.findByUniqueId(paymentDTO.getUniqueId());

        //Might be improved by using Spring MVC Security
        if (!paymentDTO.getToken().equals(player.getToken())) {
            throw new PlayerNotFoundException(Constants.PLAYER_NOT_FOUND);
        }
        switch (paymentDTO.getCommand()) {
            case "deposit":
                return deposit(paymentDTO);
            case "withdraw":
                return withdraw(paymentDTO);
            default:
                throw new CommandNotFoundException(Constants.COMMAND_NOT_FOUND);
        }

    }

    private Payment withdraw(PaymentDTO paymentDTO) throws InsufficientBalanceException {
        Player player = playerRepository.findByUniqueId(paymentDTO.getUniqueId());

        if (player.getBalance().compareTo(paymentDTO.getAmount()) <= 0) {
            throw new InsufficientBalanceException(Constants.INSUFFICIENT_BALANCE_WITHDRAW);
        }

        final Payment payment = new Payment(player, null, paymentDTO.getAmount());
        player.setBalance(player.getBalance().subtract(paymentDTO.getAmount()));

        paymentRepository.saveAndFlush(payment);
        playerRepository.saveAndFlush(player);

        return payment;
    }

    private Payment deposit(PaymentDTO paymentDTO) {
        final Player player = playerRepository.findByUniqueId(paymentDTO.getUniqueId());
        final Payment payment = new Payment(player, paymentDTO.getAmount(), null);
        player.setBalance(player.getBalance().add(paymentDTO.getAmount()));

        playerRepository.saveAndFlush(player);
        paymentRepository.saveAndFlush(payment);

        return payment;
    }
}
