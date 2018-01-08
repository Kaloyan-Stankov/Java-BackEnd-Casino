package homework_task.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class WinCoefficient {

    @Id
    private byte winningNumber;

    @Column(nullable = false)
    private BigDecimal winningCoefficient;

    public byte getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(byte winningNumber) {
        this.winningNumber = winningNumber;
    }

    public BigDecimal getWinningCoefficient() {
        return winningCoefficient;
    }

    public void setWinningCoefficient(BigDecimal winningCoefficient) {
        this.winningCoefficient = winningCoefficient;
    }
}
