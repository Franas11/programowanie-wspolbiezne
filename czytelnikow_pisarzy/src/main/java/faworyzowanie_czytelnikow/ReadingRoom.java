package faworyzowanie_czytelnikow;

import java.util.concurrent.atomic.AtomicInteger;

public class ReadingRoom {
    private volatile String dataName;
    private int dataNumber = 1;
    private final AtomicInteger readerRounds;

    public ReadingRoom(int readersRounds) {
        dataName = "data" + dataNumber;
        this.readerRounds = new AtomicInteger(readersRounds);
    }

    public int getReaderRounds() {
        return readerRounds.decrementAndGet();
    }

    public String readData() throws InterruptedException {
        return dataName;
    }

    public synchronized void writeData() throws InterruptedException {
        ++dataNumber;
        dataName = "data" + dataNumber;
//        System.out.println(Thread.currentThread().getName() + " writes: "
//                + dataName);
    }
}
