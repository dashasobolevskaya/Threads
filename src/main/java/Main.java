import thread.Direction;
import thread.Train;
import thread.traintypes.PassengerTrain;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;

public class Main {
    //Тоннель.
    // В горах через два железнодорожных тоннеля в обоих направлениях могут проезжать поезда. ???
    // Поезда к тоннелям подъезжают в любое время. ???
    // Время следования поездов по тоннелю одинаково для всех типов поездов, !
    // в туннеле одновременно могут следовать несколько поездов, но не более определенного числа.???
    // Если в тоннеле уже движется поезд и к тоннелю подъезжают другие поезда, то предпочтение следует отдать ???
    //поезду, движущемуся в том же направлении, что и поезд, который уже движется в туннеле,
    // однако подряд в одном направлении по тоннелю могут проехать не более чем определенное количество поездов. ???
    //State pattern
    //Callable
    //-Time.sleep +TimeUnit
    //WILDFLY -JBoss Seem
    //port not 80, -> 88

    public static void main(String[] args) {
        ThreadGroup firstRightGroup = new ThreadGroup("firstRight");
        ThreadGroup firstLeftGroup = new ThreadGroup("firstLeft");

        firstLeftGroup.setMaxPriority(Thread.MIN_PRIORITY);
        firstRightGroup.setMaxPriority(Thread.MIN_PRIORITY);

        LinkedBlockingDeque<Train> firstTunnelQueue = new LinkedBlockingDeque<>();

        Semaphore firstTunnel = new Semaphore(2); //10, right=2, left=3
        Semaphore firstTunnelRightTrack = new Semaphore(4);
        Semaphore firstTunnelLeftTrack = new Semaphore(4);
        /*
            ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
            Future<String> future = executorService.submit(new PassengerTrain(firstTunnel, firstTunnelRightTrack, Direction.RIGHT));
            listFuture.add(future);
            future = executorService.submit(new PassengerTrain(firstTunnel, firstTunnelLeftTrack, Direction.LEFT));
            listFuture.add(future);
         */

        List<Future<String>> listFuture = new ArrayList<>(10);
        for (int i = 0; i < 5; ++i) {
            firstTunnelQueue.add(new PassengerTrain(firstTunnel, firstTunnelLeftTrack, Direction.LEFT));
            firstTunnelQueue.add(new PassengerTrain(firstTunnel, firstTunnelRightTrack, Direction.RIGHT));
        }

        while (true) {
            if (firstLeftGroup.activeCount() == 1 && firstRightGroup.activeCount() == 0) {
                for (Train train : firstTunnelQueue) { //достаются элементы с начала или с конца??
                    if (train.getDirection() == Direction.LEFT) {
                        // train.setPriority(Thread.MAX_PRIORITY);

                        firstTunnelQueue.addFirst(train);
                        firstTunnelQueue.remove(train);
                        //firstTunnelQueue.offer(train);
                        //yeld
                        break;
                    }
                }
            } else if (firstLeftGroup.activeCount() == 0 && firstRightGroup.activeCount() == 1) {
                for (Train train : firstTunnelQueue) {
                    if (train.getDirection() == Direction.RIGHT) {
                        //train.setPriority(Thread.MAX_PRIORITY);
                        //train.
                        firstTunnelQueue.addFirst(train);
                        firstTunnelQueue.remove(train);
                        break;
                    }
                }
            }
            try {
                //if (!firstTunnelQueue.isEmpty()) {
                firstTunnelQueue.take().call(); //TAKE or TAKEFIRST?????????
                //}
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}