public class DThread extends Thread{
    private final Counter referenceToCounterObject;
    private final Semafor semafor;

    public DThread(Counter referenceToCounterObject, Semafor semafor) {
        this.referenceToCounterObject = referenceToCounterObject;
        this.semafor = semafor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; ++i) {
            try {
                semafor.V();
                referenceToCounterObject.dec();
                semafor.P();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
