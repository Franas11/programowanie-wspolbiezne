package zamek;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Fork {
    private final Lock lock;

    public Fork() {
        lock = new ReentrantLock();
    }

    public void pickUpTheFork() {
        lock.lock();
    }

    public void putTheForkDown() {
        lock.unlock();
    }
}
