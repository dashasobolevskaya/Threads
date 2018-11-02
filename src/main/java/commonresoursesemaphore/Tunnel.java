package commonresoursesemaphore;

import thread.Direction;

import java.util.concurrent.Semaphore;

public class Tunnel extends Semaphore{
    Direction direction;

    public Tunnel(int permits, Direction direction) {
        super(permits);
        this.direction=direction;
    }

    public Tunnel(int permits, boolean fair, Direction direction) {
        super(permits, fair);
        this.direction=direction;
    }
}
