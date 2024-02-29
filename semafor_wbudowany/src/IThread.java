public class IThread extends Thread {
    private final Counter referenceToCounterObject;

    public IThread(Counter referenceToCounterObject) {
        this.referenceToCounterObject = referenceToCounterObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; ++i) {
            try {
                referenceToCounterObject.inc();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}