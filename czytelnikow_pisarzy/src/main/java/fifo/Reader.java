package fifo;

public class Reader implements Runnable {
    private final ReadingRoom readingRoom;
    private final int readings;

    public Reader(ReadingRoom readingRoom, int readings) {
        this.readingRoom = readingRoom;
        this.readings = readings;
    }

    @Override
    public void run() {
        for (int i = 0; i < readings; ++i) {
            try {
                readingRoom.readData();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
