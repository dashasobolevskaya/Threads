package thread;

public enum Speed {
    HIGH(5), AVERAGE(10), LOW(5);

    private int speed;

    Speed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }
}
