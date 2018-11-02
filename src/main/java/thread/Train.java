package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public abstract class Train implements Callable<String> {
    private Semaphore semaphoreTunnel;
    private Semaphore semaphoreRoad;
    private Direction direction;
    private Speed speed;
    
    public Train(Semaphore semaphoreTunnel, Semaphore semaphoreRoad, Direction direction, Speed speed) {
        this.semaphoreTunnel = semaphoreTunnel;
        this.semaphoreRoad = semaphoreRoad;
        this.direction = direction;
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public String call() throws Exception {
        System.out.println("Train " + getClass().getName() + " is waiting, direction=" + direction);
        try {
            semaphoreRoad.acquire();
            semaphoreTunnel.acquire();
            System.out.println("train " + this.getClass().getName() + " GOES through a tunnel, direction=" + direction);
            TimeUnit.SECONDS.sleep(speed.getSpeed());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("train " + this.getClass().getName() + " LEFT a tunnel, direction=" + direction);
            semaphoreTunnel.release();
            semaphoreRoad.release();
        }
        return getClass().getName();
    }
}
