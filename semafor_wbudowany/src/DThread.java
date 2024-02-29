public class DThread extends Thread {
    private final Counter referenceToCounterObject;

    public DThread(Counter referenceToCounterObject) {
        this.referenceToCounterObject = referenceToCounterObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; ++i) {
            try {
                referenceToCounterObject.dec();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
