package homework_task.controllers;


import homework_task.entities.Payment;
import homework_task.entities.Player;
import homework_task.exceptionhandler.GameTypeNotFoundException;
import homework_task.exceptionhandler.InsufficientBalanceException;
import homework_task.exceptionhandler.PlayerExistException;
import homework_task.exceptionhandler.PlayerNotFoundException;
import homework_task.generators.TokenGenerator;
import homework_task.models.AuthenticationDTO;
import homework_task.models.BetDTO;
import homework_task.models.PaymentDTO;
import homework_task.responses.LoginAbstractResponse;
import homework_task.responses.AbstractResponse;
import homework_task.responses.BaseAbstractResponse;
import homework_task.services.BetService;
import homework_task.services.LoginService;
import homework_task.services.PaymentService;
import homework_task.services.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
public class RequestController {

    @Autowired
    private SignUpService signUpService;
    @Autowired
    private LoginService loginService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BetService betService;

    @RequestMapping(value = "/authentication",method = RequestMethod.POST)
    public @ResponseBody
    AbstractResponse authentication(@RequestBody AuthenticationDTO authenticationDTO) throws PlayerExistException, PlayerNotFoundException {
        switch (authenticationDTO.getCommand()){
            case "signup":
                final Player player = signUpService.signUp(authenticationDTO);
                return new BaseAbstractResponse(player.getUniqueId(),HttpStatus.OK);
            case "login":
                final Player loginResult = loginService.login(authenticationDTO);
                return new LoginAbstractResponse(loginResult.getUniqueId(),HttpStatus.OK, TokenGenerator.getToken());
                default:
                   throw new IllegalArgumentException("No such command");
        }
    }

    @RequestMapping(value = "/payment",method = RequestMethod.POST)
    public BaseAbstractResponse payment(@RequestBody PaymentDTO payment) throws InsufficientBalanceException {
        final Payment paymentResult = paymentService.processPayment(payment);
        return new BaseAbstractResponse(paymentResult.getPlayerId().getUniqueId(),HttpStatus.OK);
    }

    @RequestMapping(value = "/bet",method = RequestMethod.POST)
    public String bet(@RequestBody BetDTO bet) throws InsufficientBalanceException, GameTypeNotFoundException {
        betService.placeBet(bet);
        return "";
    }
}
