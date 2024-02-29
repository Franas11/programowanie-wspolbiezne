package fifo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ReadingRoom {
    private final ArrayBlockingQueue<String> dataBuffer;
    private final AtomicInteger dataNumber;

    public ReadingRoom(int bufferSize) {
        dataBuffer = new ArrayBlockingQueue<>(bufferSize);
        dataNumber = new AtomicInteger(0);
    }

    public synchronized void writeData() throws InterruptedException {
        int number = dataNumber.incrementAndGet();
        String newData = "data" + number;
        dataBuffer.put(newData);
        System.out.println(
                Thread.currentThread().getName() + " writes: "
                        + newData
        );
    }

    public void readData() throws InterruptedException {
        String data = dataBuffer.take();
        System.out.println(
                Thread.currentThread().getName()
                        + " reads: " + data
        );
    }
}