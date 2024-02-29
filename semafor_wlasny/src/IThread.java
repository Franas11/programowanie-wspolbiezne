public class IThread extends Thread{
    private final Counter referenceToCounterObject;
    private final Semafor semafor;

    public IThread(Counter referenceToCounterObject, Semafor semafor) {
        this.referenceToCounterObject = referenceToCounterObject;
        this.semafor = semafor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; ++i) {
            try {
                semafor.V();
                referenceToCounterObject.inc();
                semafor.P();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}