package thread.traintypes;

import thread.Direction;
import thread.Speed;
import thread.Train;

import java.util.concurrent.Semaphore;

public class PassengerTrain extends Train {
    private static final Speed speed = Speed.LOW;

    public PassengerTrain(Semaphore semaphoreTunnel, Semaphore semaphoreRoad, Direction direction) {
        super(semaphoreTunnel, semaphoreRoad, direction, speed);
    }
}
