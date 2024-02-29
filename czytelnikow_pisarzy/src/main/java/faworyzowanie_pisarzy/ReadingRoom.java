package faworyzowanie_pisarzy;

import java.util.concurrent.atomic.AtomicInteger;

public class ReadingRoom {
    private volatile String dataName;
    private int dataNumber = 1;
    private final AtomicInteger writerRounds;

    public ReadingRoom(int writerRounds) {
        dataName = "data" + dataNumber;
        this.writerRounds = new AtomicInteger(writerRounds);
    }

    public int decreaseWriterRounds() {
        return writerRounds.decrementAndGet();
    }

    public AtomicInteger getWriterRounds() {
        return writerRounds;
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
