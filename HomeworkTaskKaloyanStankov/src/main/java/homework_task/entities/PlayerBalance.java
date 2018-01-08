//package homework_task.entities;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
//@Entity
//@Table(name = "players_balance")
//public class PlayerBalance {
//
//
//    @OneToOne
//    @JoinColumn(name = "uniqueId")
//    private Player playerId;
//
//    @Column(nullable = false)
//    private BigDecimal balance;
//
//    public PlayerBalance(Player playerId, BigDecimal balance) {
//        this.playerId = playerId;
//        this.balance = balance;
//    }
//
//    public PlayerBalance() {
//    }
//
//    public Player getPlayerId() {
//        return playerId;
//    }
//
//    public void setPlayerId(Player playerId) {
//        this.playerId = playerId;
//    }
//
//    public BigDecimal getBalance() {
//        return balance;
//    }
//
//    public void setBalance(BigDecimal balance) {
//        this.balance = balance;
//    }
//}
