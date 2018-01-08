package homework_task.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bets")
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long betId;

    @OneToOne
    @JoinColumn(name = "uniqueId")
    private Player playerId;

    @Column(nullable = false)
    private BigDecimal betAmount;

    private boolean isWin;

    private BigDecimal winAmount;

    public Bet(Player playerId, BigDecimal betAmount, boolean isWin, BigDecimal winAmount) {
        this.playerId = playerId;
        this.betAmount = betAmount;
        this.isWin = isWin;
        this.winAmount = winAmount;
    }

    public Bet() {
    }

    public long getBetId() {
        return betId;
    }

    public void setBetId(long betId) {
        this.betId = betId;
    }

    public Player getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Player playerId) {
        this.playerId = playerId;
    }

    public BigDecimal getBetAmount() {
        return betAmount;
    }

    public void setBetAmount(BigDecimal betAmount) {
        this.betAmount = betAmount;
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }

    public BigDecimal getWinAmount() {
        return winAmount;
    }

    public void setWinAmount(BigDecimal winAmount) {
        this.winAmount = winAmount;
    }
}
