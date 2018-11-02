package thread.traintypes;

import thread.Direction;
import thread.Speed;
import thread.Train;

import java.util.concurrent.Semaphore;

public class HighSpeedTrain extends Train {
    private static final Speed speed = Speed.HIGH;

    public HighSpeedTrain( Semaphore semaphoreTunnel, Semaphore semaphoreRoad, Direction direction) {
        super( semaphoreTunnel, semaphoreRoad, direction, speed);
    }
}
