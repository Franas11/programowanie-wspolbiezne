package faworyzowanie_czytelnikow;

import java.util.concurrent.Semaphore;

public class Writer implements Runnable {
    private final ReadingRoom readingRoom;
    private final Semaphore semaphore;
    private final int writings;

    public Writer(ReadingRoom readingRoom, Semaphore semaphore,
                  int writings) {
        this.readingRoom = readingRoom;
        this.semaphore = semaphore;
        this.writings = writings;
    }

    @Override
    public void run() {
        for (int i = 0; i < writings; ++i) {
            try {
                semaphore.acquire();
                readingRoom.writeData();
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}