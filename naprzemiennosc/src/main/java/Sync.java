import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sync {
    private int tura;
    private boolean monitor;     //t1 if true, t2 if false
    private final Lock lock = new ReentrantLock();

    public Sync() {
        tura = 1;
        monitor = true;
    }

    public synchronized boolean isMonitor() {
        return monitor;
    }

    public void reverseTerm() {
        monitor = !monitor;
    }

    public void taskForThread() throws InterruptedException {
       lock.lock();
       try {
           System.out.println(
                   Thread.currentThread().getName() + ": tura -> " + tura);
           ++tura;
           reverseTerm();
       }
       finally {
           lock.unlock();
       }
    }
}
