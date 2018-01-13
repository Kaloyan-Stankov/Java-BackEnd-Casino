package homework_task.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class WinCoefficient {

    @Id
    private int winningNumber;

    @Column(nullable = false)
    private Double winningCoefficient;

    public int getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(int winningNumber) {
        this.winningNumber = winningNumber;
    }

    public Double getWinningCoefficient() {
        return winningCoefficient;
    }

    public void setWinningCoefficient(Double winningCoefficient) {
        this.winningCoefficient = winningCoefficient;
    }
}
