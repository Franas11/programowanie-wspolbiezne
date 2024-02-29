package faworyzowanie_pisarzy;

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
                semaphore.acquire();
                String data = readingRoom.readData();
//                System.out.println(
//                        Thread.currentThread().getName() + " reads: "
//                                + data);
                if (readingRoom.getWriterRounds().get() == 0) {
                    semaphore.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
