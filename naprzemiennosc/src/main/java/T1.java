public class T1 extends Thread {
    private final Sync sync;

    public T1(Sync sync) {
        super("T1");
        this.sync = sync;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; ++i) {
            try {
                while(!sync.isMonitor()) {
                   sleep(1);
                }
                sync.taskForThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
