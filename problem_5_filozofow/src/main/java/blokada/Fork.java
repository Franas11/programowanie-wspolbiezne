package blokada;

import java.util.concurrent.atomic.AtomicBoolean;

public class Fork {
    private final AtomicBoolean ifRaise;

    public Fork() {
        this.ifRaise = new AtomicBoolean(false);
    }

    public boolean pickUpTheFork() {
        return !ifRaise.compareAndExchangeAcquire(false, true);
    }

    public boolean putTheForkDown() {
        return ifRaise.compareAndExchangeAcquire(true, false);
    }
}
