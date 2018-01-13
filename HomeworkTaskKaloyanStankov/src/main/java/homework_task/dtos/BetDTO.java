package homework_task.dtos;

import java.math.BigDecimal;
import java.util.List;

public class BetDTO {
    private BigDecimal amount;
    private String gameType;
    private List<BetPairDTO> additionalData;
    private String command;
    private String uniqueId;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getGameType() {
        return gameType;
    }

    public void setGameType(String gameType) {
        this.gameType = gameType;
    }

    public List<BetPairDTO> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(List<BetPairDTO> additionalData) {
        this.additionalData = additionalData;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }
}
