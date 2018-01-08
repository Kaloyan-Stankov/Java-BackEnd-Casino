package homework_task.services;

import homework_task.entities.Payment;
import homework_task.entities.Player;
import homework_task.exceptionhandler.InsufficientBalanceException;
import homework_task.models.PaymentDTO;
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

    public Payment processPayment(PaymentDTO paymentDTO) throws InsufficientBalanceException {
        switch (paymentDTO.getCommand()) {
            case "deposit":
                //TODO TOKEN
                return deposit(paymentDTO);
            case "withdraw":
                //TODO TOKEN
                return withrdaw(paymentDTO);
            default:
                throw new IllegalArgumentException("No such command");

        }

    }

    private Payment withrdaw(PaymentDTO paymentDTO) throws InsufficientBalanceException {
        final Player player = playerRepository.findByUniqueId(paymentDTO.getUniqueId());

        if (player.getBalance().compareTo(paymentDTO.getAmount()) <= 0){
            throw new InsufficientBalanceException("InsufficientBalance. Your current balance:" + player.getBalance());
        }

        final Payment payment = paymentRepository.saveAndFlush(new Payment(player,null , paymentDTO.getAmount()));
        player.setBalance(player.getBalance().subtract(paymentDTO.getAmount()));
        playerRepository.saveAndFlush(player);

        return payment;
    }

    private Payment deposit(PaymentDTO paymentDTO) {
        final Player player = playerRepository.findByUniqueId(paymentDTO.getUniqueId());

        final Payment payment = paymentRepository.saveAndFlush(new Payment(player, paymentDTO.getAmount(), null));
        player.setBalance(player.getBalance().add(paymentDTO.getAmount()));
        playerRepository.saveAndFlush(player);

        return payment;
    }
}
