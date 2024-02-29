import java.util.concurrent.Semaphore;

public class Counter {
    private int _val;
    private final Semaphore semaphore;

    public Counter(int n) {
        _val = n;
        semaphore = new Semaphore(1);
    }

    public void inc() throws InterruptedException {
        semaphore.acquire();
        _val++;
        semaphore.release();
    }

    public void dec() throws InterruptedException {
        semaphore.acquire();
        _val--;
        semaphore.release();
    }

    public int value() {
        return _val;
    }
}