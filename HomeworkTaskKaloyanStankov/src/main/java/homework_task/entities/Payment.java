package homework_task.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    @OneToOne
    @JoinColumn(name = "uniqueId")
    private Player playerId;

    private BigDecimal deposit;
    private BigDecimal withdraw;

    public Payment(Player playerId, BigDecimal deposit, BigDecimal withdraw) {
        this.playerId = playerId;
        this.deposit = deposit;
        this.withdraw = withdraw;
    }

    public Payment() {
    }

    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public BigDecimal getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }
}
