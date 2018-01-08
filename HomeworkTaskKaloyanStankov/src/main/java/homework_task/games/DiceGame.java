package homework_task.games;

import homework_task.interfaces.Playable;

import java.util.concurrent.ThreadLocalRandom;

public class DiceGame implements Playable {

    @Override
    public Integer playGame() {
        return ThreadLocalRandom.current().nextInt(2, 12 + 1);
    }

}
