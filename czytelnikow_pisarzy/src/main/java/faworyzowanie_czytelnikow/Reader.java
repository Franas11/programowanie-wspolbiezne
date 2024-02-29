package faworyzowanie_czytelnikow;

import java.util.concurrent.Semaphore;

public class Reader implements Runnable {
    private final ReadingRoom readingRoom;
    private final Semaphore semaphore;
    private final int readings;

    public Reader(ReadingRoom readingRoom, Semaphore semaphore,
                  int readings) {
        this.readingRoom = readingRoom;
        this.semaphore = semaphore;
        this.readings = readings;
    }

    @Override
    public void run() {
        for (int i = 0; i < readings; ++i) {
            try {
                String data = readingRoom.readData();
//                System.out.println(
//                        Thread.currentThread().getName() + " reads: "
//                                + data);
                if (readingRoom.getReaderRounds() == 0) {
                    semaphore.release(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
