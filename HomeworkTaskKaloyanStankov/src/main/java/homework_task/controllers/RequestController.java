package homework_task.controllers;


import homework_task.entities.Bet;
import homework_task.entities.Payment;
import homework_task.entities.Player;
import homework_task.exceptions.*;
import homework_task.dtos.AuthenticationDTO;
import homework_task.dtos.BetDTO;
import homework_task.Constants;
import homework_task.dtos.PaymentDTO;
import homework_task.responses.BetResponse;
import homework_task.responses.LoginResponse;
import homework_task.responses.AbstractResponse;
import homework_task.responses.BaseResponse;
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

    @RequestMapping(value = "/authentication", method = RequestMethod.POST)
    public @ResponseBody
    AbstractResponse authentication(@RequestBody AuthenticationDTO authenticationDTO) throws PlayerExistException, PlayerNotFoundException, CommandNotFoundException {
        switch (authenticationDTO.getCommand()) {
            case "signup":
                final Player player = signUpService.signUp(authenticationDTO);
                return new BaseResponse(player.getUniqueId(), HttpStatus.OK);
            case "login":
                final Player loginResult = loginService.login(authenticationDTO);
                return new LoginResponse(loginResult.getUniqueId(), HttpStatus.OK, loginResult.getToken());
            default:
                throw new CommandNotFoundException(Constants.COMMAND_NOT_FOUND);
        }
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public BaseResponse payment(@RequestBody PaymentDTO payment) throws InsufficientBalanceException, CommandNotFoundException, PlayerNotFoundException {
        final Payment paymentResult = paymentService.processPayment(payment);
        return new BaseResponse(paymentResult.getPlayerId().getUniqueId(), HttpStatus.OK);
    }

    //Used Request in format:
    // {"amount":10.0,"gameType":"dice","additionalData":[{"u":7,"v":10.0}],"command":"bet","uniqueId":"add59117-ee9f-4379-8d41-ae29b6626ce2"} 
    @RequestMapping(value = "/bet", method = RequestMethod.POST)
    public AbstractResponse bet(@RequestBody BetDTO bet) throws InsufficientBalanceException, GameTypeNotFoundException {
        final Bet betResult = betService.placeBet(bet);
        return new BetResponse(betResult.getPlayerId().getUniqueId(), HttpStatus.OK, betResult.getWinAmount(), betResult.getOutcome());
    }
}
