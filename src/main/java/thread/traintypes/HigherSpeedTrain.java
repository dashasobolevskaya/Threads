package thread.traintypes;

import thread.Direction;
import thread.Speed;
import thread.Train;

import java.util.concurrent.Semaphore;

public class HigherSpeedTrain extends Train {
    private static final Speed speed = Speed.AVERAGE;

    public HigherSpeedTrain(Semaphore semaphoreTunnel, Semaphore semaphoreRoad, Direction direction) {
        super(semaphoreTunnel, semaphoreRoad, direction, speed);
    }
}
