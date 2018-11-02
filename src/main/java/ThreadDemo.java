public class ThreadDemo extends Thread {
    @Override
    public synchronized void start() {
        System.out.println('e');
        super.start();
        System.out.println('e');
    }

    @Override
    public void run() {
        System.out.println("Thread deom");
    }
}
