public class IThread extends Thread{
    private final Counter referenceToCounterObject;

    public IThread(Counter referenceToCounterObject) {
        this.referenceToCounterObject = referenceToCounterObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10000; ++i) {
            referenceToCounterObject.inc();
        }
    }
}
