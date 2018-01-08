package homework_task.models;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BetDTO {
    private BigDecimal amount;
    private String gameType;
    private List<BetPair> additionalData;
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

    public List<BetPair> getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(List<BetPair> additionalData) {
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
