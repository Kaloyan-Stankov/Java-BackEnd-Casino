package homework_task.responses;

import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public class BetResponse extends AbstractResponse {
    private BigDecimal winAmount;
    private int outcome;

    public BetResponse(String uniqueId, HttpStatus status, BigDecimal winAmount, int outcome) {
        this.setWinAmount(winAmount);
        this.setOutcome(outcome);
        setUniqueId(uniqueId);
        setStatus(status);
    }

    public BigDecimal getWinAmount() {
        return winAmount;
    }

    private void setWinAmount(BigDecimal winAmount) {
        this.winAmount = winAmount;
    }

    public int getOutcome() {
        return outcome;
    }

    private void setOutcome(int outcome) {
        this.outcome = outcome;
    }
}
